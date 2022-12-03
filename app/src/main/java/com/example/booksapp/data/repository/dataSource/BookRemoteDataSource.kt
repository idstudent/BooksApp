package com.example.booksapp.data.repository.dataSource

import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksModel
import kotlinx.coroutines.flow.Flow

interface BookRemoteDataSource {
    fun getNewBookList(categoryId : Int) : Flow<List<Books>>
    fun getRecommendBookList() : Flow<List<Books>>
    fun getBestSellerList(categoryId: Int) : Flow<List<BooksModel.Response.BooksItem>>
    fun getBookDetailInfo(isbn : String, queryType : String, searchType : String) : Flow<List<BooksModel.Response.BooksItem>>
}