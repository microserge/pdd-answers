package com.microserge.pddanswers.di

import com.microserge.pddanswers.core.data.HttpClientFactory
import com.microserge.pddanswers.question.data.network.KtorRemoteQuestionDataSource
import com.microserge.pddanswers.question.data.repository.DefaultQuestionRepository
import com.microserge.pddanswers.question.data.network.RemoteQuestionDataSource
import com.microserge.pddanswers.question.domain.QuestionRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteQuestionDataSource).bind<RemoteQuestionDataSource>()
    singleOf(::DefaultQuestionRepository).bind<QuestionRepository>()
}