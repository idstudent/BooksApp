package com.example.booksapp.book_detail_feature.presentation.state

import com.example.booksapp.core.domain.model.Book

data class BookDetailState (
    val bookDetailInfo: List<Book>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)
