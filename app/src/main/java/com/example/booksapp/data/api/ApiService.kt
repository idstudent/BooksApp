package com.example.booksapp.data.api

import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.data.api.model.BooksModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        val BASE_URL = "https://book.interpark.com/"
    }

    @GET("api/newBook.api")
    suspend fun getNewBooks(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int,
        @Query("output") output : String = "json"
    ) : BooksModel.Response

    @GET("api/newBook.api")
    suspend fun getBookList(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int,
        @Query("output") output : String = "json"
    ): BookListResponse

    @GET("api/recommend.api")
    suspend fun getRecommendBookList(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int,
        @Query("output") output : String = "json"
    ): BookListResponse


    @GET("api/recommend.api")
    suspend fun getRecommendBooks(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int,
        @Query("output") output : String = "json"
    ): BooksModel.Response

    @GET("api/bestSeller.api")
    suspend fun getBestSellerBooks(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int,
        @Query("output") output : String = "json"
    ): BooksModel.Response

    @GET("api/search.api")
    suspend fun getSearchBook(
        @Query("key") key : String,
        @Query("query") title : String,
        @Query("queryType") queryType : String,
        @Query("searchTarget") searchType : String = "book",
        @Query("start") page : Int = 1,
        @Query("sort") sort : String = "accuracy",
        @Query("soldOut") soldOutType : String = "y",
        @Query("output") output : String = "json"
    ): BooksModel.Response
}