package com.example.booksapp.data.repository.dataSource

import com.example.booksapp.data.api.model.Books
import com.example.booksapp.data.api.model.BooksModel
import kotlinx.coroutines.flow.Flow

interface BookRemoteDataSource {
    fun getNewBookList(categoryId : Int) : Flow<List<Books>>
    fun getRecommendBookList() : Flow<List<Books>>
    fun getBestSellerList(categoryId: Int) : Flow<List<BooksModel.Response.BooksItem>>
    fun getAllNewBookList(categoryId: Int) : Flow<List<BooksModel.Response.BooksItem>>
    fun getAllRecommendBookList() : Flow<List<BooksModel.Response.BooksItem>>
    fun getBookDetailInfo(isbn : String, queryType : String, searchType : String) : Flow<List<BooksModel.Response.BooksItem>>
}