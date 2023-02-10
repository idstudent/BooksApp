package com.example.booksapp.presentation.di

import com.example.booksapp.data.repository.dataSource.BookLocalDataSource
import com.example.booksapp.data.repository.dataSourceImpl.BookLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataModule {
    @Singleton
    @Binds
    abstract fun provideBookLocalDataSource(
        bookLocalImpl : BookLocalDataSourceImpl
    ) : BookLocalDataSource
}