package com.microserge.pddanswers

//https://pdd-answer.online/api/questions?search=&page=2
data class Question(
    val chapter: Int,
    val id: String,
    val image: String,
    val source: String,
    val title: String
)
