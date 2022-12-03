package com.example.booksapp.data.repository.dataSource

import com.example.booksapp.data.api.model.BooksModel
import kotlinx.coroutines.flow.Flow

interface BookLocalDataSource {
    fun selectBook() : Flow<List<BooksModel.Response.BooksItem>>
    suspend fun insertBook(book: BooksModel.Response.BooksItem)
    suspend fun deleteBook(book: BooksModel.Response.BooksItem)
    fun selectBookReport() : Flow<List<BooksModel.Response.BooksItem>>
    suspend fun insertBookReport(book: BooksModel.Response.BooksItem)
}