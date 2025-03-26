package com.microserge.pddanswers.ui.presentation.question_list

import com.microserge.pddanswers.core.presentation.UiText
import com.microserge.pddanswers.question.domain.Question

data class QuestionListState(
    val searchQuery: String = "",
    val searchResult: List<Question> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: UiText? = null,
    val totalPages: Int = 0,
    val currentPage: Int = 1
)
