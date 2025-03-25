package com.microserge.pddanswers.data.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

import com.microserge.pddanswers.ui.presentation.question_list.SearchViewModel

val viewModelsModule = module {
    viewModelOf(::SearchViewModel)
}