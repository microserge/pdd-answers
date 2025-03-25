package com.microserge.pddanswers.ui.presentation.question_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.microserge.pddanswers.Question
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

class SearchViewModel() : ViewModel() {

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
                    it.copy(searchQuery = action.query)
                }
            }
        }
    }

    private fun observeSearchQuery() {
        state
            .map { it.searchQuery }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { query ->
                when {
                    query.isBlank() -> {
                        _state.update {
                            it.copy(
                                errorMessage = null,
                                searchResult = cachedQuestions
                            )
                        }
                    }

                    query.length >= 2 -> {
                        searchJob?.cancel()
                        searchJob = searchQuestions(query)
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    private fun searchQuestions(query: String) = viewModelScope.launch {
        _state.update {
            it.copy(isLoading = true)
        }

        //get from api
    }
}