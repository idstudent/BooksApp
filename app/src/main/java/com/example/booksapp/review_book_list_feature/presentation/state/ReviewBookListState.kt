package com.example.booksapp.review_book_list_feature.presentation.state

import com.example.booksapp.core.domain.model.Book

data class ReviewBookListState(
    val books: List<Book> = emptyList()
)