package com.example.booksapp.book_detail_feature.data.repository

import com.example.booksapp.book_like_feature.domain.BookLikeLocalDataSource
import com.example.booksapp.book_like_feature.domain.BookLikeRepository
import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookLikeRepositoryImpl @Inject constructor(
    private val localDataSource: BookLikeLocalDataSource
): BookLikeRepository {
    override fun getBookList(): Flow<List<Book>> {
        return localDataSource.selectBook()
    }

    override suspend fun insert(book: Book) {
        localDataSource.insertBook(book)
    }

    override suspend fun delete(book: Book) {
        localDataSource.deleteBook(book)
    }

    override suspend fun isLike(bookId: Int): Boolean {
        return localDataSource.isLike(bookId)
    }
}