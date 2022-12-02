package com.example.booksapp.presentation.module

import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.data.repository.dataSourceImpl.BookRemoteDateSourceImpl
import org.koin.dsl.module

val remoteDataModule = module {
    single<BookRemoteDataSource> { BookRemoteDateSourceImpl(apiService = get())}
}
