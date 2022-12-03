package com.example.booksapp.presentation.module

import com.example.booksapp.data.repository.BookRepositoryImpl
import com.example.booksapp.domain.repository.BookRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BookRepository> {
        BookRepositoryImpl(bookRemoteDataSource = get(), bookLocalDataSource = get())
    }
}