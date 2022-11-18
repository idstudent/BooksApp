package com.example.booksapp.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        val BASE_URL = "http://book.interpark.com/"
    }

    @GET("api/newBook.api")
    suspend fun getNewBooks(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int
    )

    @GET("api/recommend.api")
    suspend fun getRecommendBooks(
        @Query("key") key : String,
        @Query("categoryId") categoryId : Int
    )
}