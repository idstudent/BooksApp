package com.example.booksapp.presentation.module

import com.example.booksapp.presentation.viewmodel.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        BooksViewModel(
            bookRepository = get(),
            getNewBookListUseCase = get(),
            getRecommendBookListUseCase = get()
        )
    }
    viewModel {
        BestSellerViewModel(
            getBestSellerBookListUseCase = get()
        )
    }

    viewModel {
        BookDetailViewModel(
            getBookDetailInfoUseCase = get(),
            localBookMarkUseCase = get()
        )
    }

    viewModel {
        BookReportViewModel(
            localBookReportUseCase = get()
        )
    }

    viewModel {
        AllBookListViewModel(
            getAllNewBookListUseCase = get(),
            getRecommendBookListUseCase = get()
        )
    }
}