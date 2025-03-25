package com.microserge.pddanswers

import android.app.Application
import com.microserge.pddanswers.di.networkModule
import com.microserge.pddanswers.di.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()

            androidContext(this@MainApplication)

            modules(viewModelsModule, networkModule)
        }
    }
}