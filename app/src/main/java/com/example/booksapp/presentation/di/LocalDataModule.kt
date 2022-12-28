package com.example.booksapp.presentation.di

import com.example.booksapp.data.db.BookMarkDatabase
import com.example.booksapp.data.db.BookReportDatabase
import com.example.booksapp.data.repository.dataSource.BookLocalDataSource
import com.example.booksapp.data.repository.dataSourceImpl.BookLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {
    @Singleton
    @Provides
    fun provideBookLocalDataSource(
        bookMarkDatabase: BookMarkDatabase,
        bookReportDatabase: BookReportDatabase
    ) : BookLocalDataSource =
        BookLocalDataSourceImpl(bookMarkDatabase, bookReportDatabase)
}