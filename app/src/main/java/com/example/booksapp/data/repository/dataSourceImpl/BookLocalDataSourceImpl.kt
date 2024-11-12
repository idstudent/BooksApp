package com.example.booksapp.data.repository.dataSourceImpl

import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.data.repository.dataSource.BookLocalDataSource
import com.example.booksapp.data.db.BookMarkDatabase
import com.example.booksapp.data.db.BookReportDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookLocalDataSourceImpl @Inject constructor(
    private val bookMarkDatabase: BookMarkDatabase,
    private val bookReportDatabase: BookReportDatabase
) : BookLocalDataSource {
    override fun selectBook() : Flow<List<BooksModel.Response.BooksItem>> {
        return bookMarkDatabase.bookDao().selectBooks()
    }

    override suspend fun insertBook(book : BooksModel.Response.BooksItem)  {
        bookMarkDatabase.bookDao().insertBook(book)
    }

    override suspend fun deleteBook(book : BooksModel.Response.BooksItem)  {
        bookMarkDatabase.bookDao().deleteBook(book)
    }
    override fun selectBookReport() : Flow<List<BooksModel.Response.BooksItem>>{
        return bookReportDatabase.bookDao().selectBooks()
    }

    override suspend fun insertBookReport(book: BooksModel.Response.BooksItem) {
        bookReportDatabase.bookDao().insertBook(book)
    }
}