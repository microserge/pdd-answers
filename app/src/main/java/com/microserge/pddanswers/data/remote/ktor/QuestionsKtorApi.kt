package com.microserge.pddanswers.data.remote.ktor

interface QuestionsKtorApi {
    suspend fun getQuestions(page: Int, query: String)
}