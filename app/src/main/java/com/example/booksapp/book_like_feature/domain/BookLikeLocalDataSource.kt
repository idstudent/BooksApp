package com.example.booksapp.book_like_feature.domain

import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookLikeLocalDataSource {
    fun selectBook() : Flow<List<Book>>
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
    suspend fun isLike(bookId: Int): Boolean
}