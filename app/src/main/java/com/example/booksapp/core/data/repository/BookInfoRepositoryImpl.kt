package com.example.booksapp.core.data.repository

import com.example.booksapp.core.domain.BookInfoLocalDataSource
import com.example.booksapp.core.domain.BookInfoRepository
import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookInfoRepositoryImpl @Inject constructor(
    private val localDataSource: BookInfoLocalDataSource
): BookInfoRepository {
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

    override fun getReviewBookList(): Flow<List<Book>> {
        return localDataSource.getReviewBook()
    }
}