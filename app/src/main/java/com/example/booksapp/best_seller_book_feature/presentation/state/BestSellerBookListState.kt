package com.example.booksapp.best_seller_book_feature.presentation.state

import com.example.booksapp.core.domain.model.Book

data class BestSellerBookListState(
    val localBooks: List<Book>? = null,
    val foreignBooks: List<Book>?= null,
    val error: String = "",
    val isLoading: Boolean = false
)
