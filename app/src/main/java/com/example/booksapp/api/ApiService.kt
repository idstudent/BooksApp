package com.example.booksapp.api

import com.example.booksapp.api.model.BooksModel
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

    @GET("api/recommend.api")
    suspend fun getRecommendBooks(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int,
        @Query("output") output : String = "json"
    ): BooksModel.Response
}