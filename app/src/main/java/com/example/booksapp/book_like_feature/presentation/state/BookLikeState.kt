package com.example.booksapp.book_like_feature.presentation.state

import com.example.booksapp.core.domain.model.Book

data class BookLikeState(
    val books: List<Book> = emptyList()
)