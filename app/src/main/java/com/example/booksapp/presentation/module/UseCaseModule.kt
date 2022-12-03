package com.example.booksapp.presentation.module

import com.example.booksapp.domain.usecase.*
import org.koin.dsl.module

val useCaseModule = module {
    single { GetNewBookListUseCase(bookRepository = get()) }
    single { GetRecommendBookListUseCase(bookRepository = get()) }
    single { GetBestSellerBookListUseCase(bookRepository = get()) }
    single { GetBookDetailInfoUseCase(bookRepository = get()) }
    single { GetAllNewBookListUseCase(bookRepository = get()) }
    single { GetAllRecommendBookListUseCase(bookRepository = get()) }
    single { GetSearchBookListUseCase(bookRepository = get()) }
    single { LocalBookMarkUseCase(bookRepository = get()) }
    single { LocalBookReportUseCase(bookRepository = get()) }
}