package com.example.booksapp.books_feature.domain

import com.example.booksapp.core.domain.model.BookListResponse


interface GetBookRemoteDataSource {
    suspend fun getBookList(categoryId: Int): BookListResponse
    suspend fun getRecommendBookList(categoryId: Int): BookListResponse
    suspend fun getBestSellerBookList(categoryId: Int): BookListResponse
}