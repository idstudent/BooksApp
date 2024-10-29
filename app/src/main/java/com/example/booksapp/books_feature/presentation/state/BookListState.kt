package com.example.booksapp.books_feature.presentation.state


import com.example.booksapp.core.domain.model.Book

data class BookListState(
    val localBooks: List<Book>? = null,
    val foreignBooks: List<Book>?= null,
    val recommendedBooks: List<Book>? = null,
    val error: String = "",
    val isLoading: Boolean = false
)