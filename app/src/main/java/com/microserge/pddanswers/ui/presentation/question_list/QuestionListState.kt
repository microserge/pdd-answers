package com.microserge.pddanswers.ui.presentation.question_list

import com.microserge.pddanswers.Question

data class QuestionListState(
    val searchQuery: String = "",
    val searchResult: List<Question> = emptyList(),
    val isLoading: Boolean = true,
    val errorMessage: String? = ""
)
