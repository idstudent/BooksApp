package com.example.booksapp.data.repository.dataSource

import androidx.paging.PagingData
import com.example.booksapp.core.data.remote.model.Books
import com.example.booksapp.core.data.remote.model.BooksModel
import kotlinx.coroutines.flow.Flow

interface BookRemoteDataSource {
    fun getNewBookList(categoryId : Int) : Flow<List<Books>>
    fun getRecommendBookList() : Flow<List<Books>>
    fun getBestSellerList(categoryId: Int) : Flow<List<BooksModel.Response.BooksItem>>
    fun getAllNewBookList(categoryId: Int) : Flow<List<BooksModel.Response.BooksItem>>
    fun getAllRecommendBookList() : Flow<List<BooksModel.Response.BooksItem>>
    fun getBookDetailInfo(isbn : String, queryType : String, searchType : String) : Flow<List<BooksModel.Response.BooksItem>>
    fun getSearchBooks(query : String, queryType : String, searchType : String) : Flow<PagingData<BooksModel.Response.BooksItem>>
}