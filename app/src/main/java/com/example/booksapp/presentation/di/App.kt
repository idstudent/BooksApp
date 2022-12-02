package com.example.booksapp.presentation.di

import android.app.Application
import com.example.booksapp.presentation.module.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                repositoryModule,
                useCaseModule,
                apiManagerModule,
                remoteDataModule,
                localDataModule)
        }
    }
}