package com.microserge.pddanswers.question.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    @SerialName("chapter") val chapter: Int,
    @SerialName("id") val id: String,
    @SerialName("image") val image: String,
    @SerialName("source") val source: String,
    @SerialName("title") val title: String
)