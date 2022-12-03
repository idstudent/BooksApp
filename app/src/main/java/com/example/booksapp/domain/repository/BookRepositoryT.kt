package com.example.booksapp.domain.repository

import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksModel
import kotlinx.coroutines.flow.Flow

interface BookRepositoryT {
    fun getNewBookList(categoryId : Int) : Flow<List<Books>>
    fun getRecommendBookList() : Flow<List<Books>>
    fun getBestSellerList(categoryId : Int) : Flow<List<BooksModel.Response.BooksItem>>
    fun getBookDetailInfo(isbn : String, queryType : String, searchType : String) : Flow<List<BooksModel.Response.BooksItem>>
    fun selectBook() : Flow<List<BooksModel.Response.BooksItem>>
    suspend fun insertBook(book : BooksModel.Response.BooksItem)
    suspend fun deleteBook(book : BooksModel.Response.BooksItem)
    fun selectBookReport() : Flow<List<BooksModel.Response.BooksItem>>
    suspend fun insertBookReport(book : BooksModel.Response.BooksItem)
}