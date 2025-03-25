package com.microserge.pddanswers.question.data.repository

import com.microserge.pddanswers.core.domain.DataError
import com.microserge.pddanswers.core.domain.Result
import com.microserge.pddanswers.core.domain.map
import com.microserge.pddanswers.question.data.mappers.toQuestion
import com.microserge.pddanswers.question.data.network.RemoteQuestionDataSource
import com.microserge.pddanswers.question.domain.Question
import com.microserge.pddanswers.question.domain.QuestionRepository

class DefaultQuestionRepository(
    private val remoteQuestionDataSource: RemoteQuestionDataSource
) : QuestionRepository {
    override suspend fun searchQuestions(query: String): Result<List<Question>, DataError.Remote> {
        return remoteQuestionDataSource.searchQuestions(query)
            .map { dto ->
                dto.data.map {
                    it.toQuestion()
                }
            }
    }
}