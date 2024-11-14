package com.example.booksapp.book_detail_feature.presentation

import com.example.booksapp.core.domain.model.Book


sealed class BookDetailEvent {
    data class AddLikeBook(val book: Book): BookDetailEvent()
    data class DeleteLikeBook(val book: Book): BookDetailEvent()
    data class IsLikeBook(val bookId: Int): BookDetailEvent()

}

