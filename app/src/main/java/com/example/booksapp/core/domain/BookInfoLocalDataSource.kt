package com.example.booksapp.core.domain

import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookInfoLocalDataSource {
    fun selectBook() : Flow<List<Book>>
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
    suspend fun isLike(bookId: Int): Boolean
    fun getReviewBook(): Flow<List<Book>>
}