package com.microserge.pddanswers.question.data.network

import com.microserge.pddanswers.core.domain.DataError
import com.microserge.pddanswers.core.domain.Result
import com.microserge.pddanswers.question.data.dto.SearchResponseDto

interface RemoteQuestionDataSource {
    suspend fun searchQuestions(
        query: String,
        page: Int = 0
    ): Result<SearchResponseDto, DataError.Remote>
}

