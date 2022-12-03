package com.example.booksapp.presentation.module

import com.example.booksapp.data.repository.dataSource.BookLocalDataSource
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.data.repository.dataSourceImpl.BookLocalDataSourceImpl
import com.example.booksapp.data.repository.dataSourceImpl.BookRemoteDataSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single<BookRemoteDataSource> { BookRemoteDataSourceImpl(apiService = get()) }
    single<BookLocalDataSource> {
        BookLocalDataSourceImpl(
            bookMarkDatabase = get(),
            bookReportDatabase = get()
        )
    }
}
