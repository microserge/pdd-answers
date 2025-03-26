package com.microserge.pddanswers.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

import com.microserge.pddanswers.ui.presentation.question_list.SearchViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf

val viewModelsModule = module {
    viewModel { SearchViewModel(get()) }
}

 val platformModule: Module
    get() = module {
        single<HttpClientEngine> { OkHttp.create() }
    }
