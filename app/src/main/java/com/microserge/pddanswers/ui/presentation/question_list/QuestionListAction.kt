package com.microserge.pddanswers.ui.presentation.question_list

import com.microserge.pddanswers.question.domain.Question

sealed interface QuestionListAction {
    data class OnSearchQueryChange(val query: String) : QuestionListAction
    data class OnQuestionClick(val question: Question) : QuestionListAction
    data object LoadMore : QuestionListAction
}