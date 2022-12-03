package com.example.booksapp.presentation.module

import com.example.booksapp.domain.usecase.GetBestSellerBookListUseCase
import com.example.booksapp.domain.usecase.GetNewBookListUseCase
import com.example.booksapp.domain.usecase.GetRecommendBookListUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { GetNewBookListUseCase(bookRepository = get()) }
    single { GetRecommendBookListUseCase(bookRepository = get()) }
    single { GetBestSellerBookListUseCase(bookRepository = get()) }
}