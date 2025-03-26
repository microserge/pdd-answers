package com.microserge.pddanswers.ui.presentation.question_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.microserge.pddanswers.core.domain.onError
import com.microserge.pddanswers.core.domain.onSuccess
import com.microserge.pddanswers.core.presentation.toUiText
import com.microserge.pddanswers.question.domain.Question
import com.microserge.pddanswers.question.domain.QuestionRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    private var cachedQuestions = emptyList<Question>()
    private var searchJob: Job? = null

    private val _state = MutableStateFlow(QuestionListState())
    val state = _state
        .onStart {
            if (cachedQuestions.isEmpty()) {
                observeSearchQuery()
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: QuestionListAction) {
        when (action) {
            is QuestionListAction.OnQuestionClick -> {

            }

            is QuestionListAction.OnSearchQueryChange -> {
                _state.update {
                    it.copy(searchQuery = action.query, currentPage = 1)
                }
            }

            QuestionListAction.LoadMore -> {
                if (state.value.totalPages != state.value.currentPage)
                    _state.update {
                        it.copy(
                            currentPage = it.currentPage + 1
                        )
                    }
            }
        }
    }

    @OptIn(FlowPreview::class)
    private fun observeSearchQuery() {
        state
            .map { Pair(it.searchQuery, it.currentPage) }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                searchJob?.cancel()
                searchJob = searchQuestions(query.first, query.second)
            }
            .launchIn(viewModelScope)
    }

    private fun searchQuestions(query: String, page: Int = 1) = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true)
        }

        questionRepository
            .searchQuestions(query, page)
            .onSuccess { searchResult ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = null,
                        searchResult = if (page == 1) searchResult.first else it.searchResult + searchResult.first,
                        currentPage = searchResult.second.first,
                        totalPages = searchResult.second.second
                    )
                }
            }.onError { error ->
                _state.update {
                    it.copy(
                        searchResult = emptyList(),
                        isLoading = false,
                        errorMessage = error.toUiText()
                    )
                }
            }
    }
}