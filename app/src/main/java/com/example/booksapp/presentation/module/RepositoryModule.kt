package com.example.booksapp.presentation.module

import com.example.booksapp.data.repository.BookRepositoryImpl
import com.example.booksapp.domain.repository.BookRepositoryT
import com.example.booksapp.repository.BookRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<BookRepositoryT> { BookRepositoryImpl(bookRemoteDataSource = get()) }

    factory {
        BookRepository(
            service = get(),
            bookMarkDatabase = get(),
            reportDatabase = get()
        )
    }
}