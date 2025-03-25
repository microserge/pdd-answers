package com.microserge.pddanswers.ui.presentation.question_list

import com.microserge.pddanswers.Question

sealed interface QuestionListAction {
    data class OnSearchQueryChange(val query: String) : QuestionListAction
    data class OnQuestionClick(val question: Question) : QuestionListAction
}