package com.example.booksapp.presentation.di

import com.example.booksapp.data.repository.BookRepositoryImpl
import com.example.booksapp.data.repository.dataSource.BookLocalDataSource
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.domain.repository.BookRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideBookRepository(
        bookLocalDataSource: BookLocalDataSource,
        bookRemoteDataSource: BookRemoteDataSource
    ) : BookRepository {
        return BookRepositoryImpl(bookRemoteDataSource, bookLocalDataSource)
    }
}