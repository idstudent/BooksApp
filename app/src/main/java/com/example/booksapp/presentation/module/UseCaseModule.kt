package com.example.booksapp.presentation.module

import com.example.booksapp.domain.usecase.GetNewBookListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetNewBookListUseCase(bookRepository = get()) }
}