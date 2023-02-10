package com.example.booksapp.presentation.di

import com.example.booksapp.data.repository.BookRepositoryImpl
import com.example.booksapp.data.repository.dataSource.BookLocalDataSource
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.data.repository.dataSourceImpl.BookRemoteDataSourceImpl
import com.example.booksapp.domain.repository.BookRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun provideBookRepository(
        bookRepositoryImpl : BookRepositoryImpl
    ) : BookRepository
}