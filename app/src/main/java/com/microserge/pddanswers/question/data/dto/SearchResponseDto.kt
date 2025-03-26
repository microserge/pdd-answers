package com.microserge.pddanswers.question.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchResponseDto(
    @SerialName("data") val data: List<QuestionDto>,
    @SerialName("links") val links: Links,
    @SerialName("meta") val meta: Meta
)

@Serializable
data class Links(
    @SerialName("first") val first: String?,
    @SerialName("last") val last: String?,
    @SerialName("next") val next: String?,
    @SerialName("prev") val prev: String?
)

@Serializable
data class Meta(
    @SerialName("current_page") val currentPage: Int?,
    @SerialName("from") val from: Int?,
    @SerialName("last_page") val lastPage: Int?,
    @SerialName("path") val path: String?,
    @SerialName("per_page") val pageSize: Int?,
    @SerialName("to") val to: Int?,
    @SerialName("total") val total: Int
)