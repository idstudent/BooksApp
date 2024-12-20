package com.example.booksapp.core.data.remote

import com.example.booksapp.core.domain.model.BookListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        val BASE_URL = "https://book.interpark.com/"
    }

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

    @GET("api/bestSeller.api")
    suspend fun getBestSellerBookList(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int,
        @Query("output") output : String = "json"
    ): BookListResponse

    @GET("api/search.api")
    suspend fun getSearchBookInfo(
        @Query("key") key : String,
        @Query("query") title : String,
        @Query("queryType") queryType : String,
        @Query("searchTarget") searchType : String = "book",
        @Query("start") page : Int = 1,
        @Query("sort") sort : String = "accuracy",
        @Query("soldOut") soldOutType : String = "y",
        @Query("output") output : String = "json"
    ): BookListResponse
}