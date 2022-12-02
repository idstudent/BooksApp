package com.example.booksapp.presentation.module

import com.example.booksapp.presentation.viewmodel.BooksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        BooksViewModel(
            bookRepository = get(),
            getNewBookListUseCase = get()
        )
    }
}