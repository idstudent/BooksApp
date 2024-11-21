package com.example.booksapp.core.data.source

import com.example.booksapp.core.domain.BookInfoLocalDataSource
import com.example.booksapp.core.data.local.dao.BookDao
import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookInfoLocalDataSourceImpl @Inject constructor(
    private val dao: BookDao
): BookInfoLocalDataSource {
    override fun selectBook(): Flow<List<Book>> {
        return dao.selectBookList()
    }

    override suspend fun insertBook(book: Book) {
        dao.insertBook(book)
    }

    override suspend fun deleteBook(book: Book) {
        dao.deleteBook(book)
    }

    override suspend fun isLike(bookId: Int): Boolean {
        return dao.isLike(bookId) != null
    }

    override fun getReviewBook(): Flow<List<Book>> {
        return dao.selectReviewBooks()
    }
}