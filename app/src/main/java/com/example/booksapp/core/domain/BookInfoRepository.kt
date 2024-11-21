package com.example.booksapp.core.domain

import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookInfoRepository {
    fun getBookList(): Flow<List<Book>>
    suspend fun insert(book: Book)
    suspend fun delete(book: Book)
    suspend fun isLike(bookId: Int): Boolean

    suspend fun getReviewBookList(): List<Book>
}