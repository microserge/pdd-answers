package com.microserge.pddanswers.question.domain

data class Question(
    val chapter: Int,
    val id: String,
    val image: String,
    val source: String,
    val title: String
)
