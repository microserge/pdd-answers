package com.microserge.pddanswers.question.data.network

import com.microserge.pddanswers.core.data.safeCall
import com.microserge.pddanswers.core.domain.DataError
import com.microserge.pddanswers.core.domain.Result
import com.microserge.pddanswers.question.data.dto.SearchResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

private const val BASE_URL = "https://pdd-answer.online/api/questions"

class KtorRemoteQuestionDataSource(
    private val httpClient: HttpClient
) : RemoteQuestionDataSource {
    override suspend fun searchQuestions(
        query: String,
        page: Int
    ): Result<SearchResponseDto, DataError.Remote> {
        return safeCall<SearchResponseDto> {
            httpClient.get(urlString = BASE_URL) {
                parameter("search", query)
                parameter("page", page)
            }
        }
    }
}