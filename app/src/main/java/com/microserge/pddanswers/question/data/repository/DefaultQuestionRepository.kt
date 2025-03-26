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
    override suspend fun searchQuestions(query: String, page: Int): Result<Pair<List<Question>, Pair<Int, Int>>, DataError.Remote> {
        return remoteQuestionDataSource.searchQuestions(query, page)
            .map { dto ->
                Pair(
                    dto.data.map {
                        it.toQuestion()
                    },
                    Pair(
                        dto.meta.currentPage!!,
                        dto.meta.lastPage!!
                    )
                )
            }
    }
}