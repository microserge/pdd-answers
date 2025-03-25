package com.microserge.pddanswers.question.data.mappers

import com.microserge.pddanswers.question.data.dto.QuestionDto
import com.microserge.pddanswers.question.domain.Question

fun QuestionDto.toQuestion(): Question = Question(
    id = this.id,
    chapter = this.chapter,
    image = this.image,
    source = this.source,
    title = this.title,
)