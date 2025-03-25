package com.microserge.pddanswers.question.domain

import com.microserge.pddanswers.core.domain.DataError
import com.microserge.pddanswers.core.domain.Result

interface QuestionRepository {
    suspend fun searchQuestions(query: String): Result<List<Question>, DataError.Remote>
}