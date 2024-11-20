package com.example.booksapp.book_detail_feature.data.source

import com.example.booksapp.book_like_feature.domain.BookLikeLocalDataSource
import com.example.booksapp.core.data.local.dao.BookDao
import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookLikeLocalDataSourceImpl @Inject constructor(
    private val dao: BookDao
): BookLikeLocalDataSource {
    override fun selectBook(): Flow<List<Book>> {
        return dao.selectBookList()
    }

    override suspend fun insertLike(book: Book) {
        dao.insertLike(book)
    }

    override suspend fun deleteLike(book: Book) {
        dao.deleteLike(book)
    }

    override suspend fun isLike(bookId: Int): Boolean {
        return dao.isLike(bookId) != null
    }
}