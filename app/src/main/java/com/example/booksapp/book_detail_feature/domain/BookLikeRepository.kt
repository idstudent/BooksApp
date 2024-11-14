package com.example.booksapp.book_detail_feature.domain

import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface BookLikeRepository {
    fun getBookList(): Flow<List<Book>>
    suspend fun insert(book: Book)
    suspend fun delete(book: Book)
    suspend fun isLike(bookId: Int): Boolean
}