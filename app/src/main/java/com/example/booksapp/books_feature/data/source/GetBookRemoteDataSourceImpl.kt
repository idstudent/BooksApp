package com.example.booksapp.books_feature.data.source

import com.example.booksapp.books_feature.domain.GetBookRemoteDataSource
import com.example.booksapp.core.data.ApiKey
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.data.api.ApiService
import javax.inject.Inject

class GetBookRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
): GetBookRemoteDataSource {


    override suspend fun getBookList(categoryId: Int): BookListResponse {
        return service.getBookList(ApiKey.apiKey, categoryId)
    }

    override suspend fun getRecommendBookList(categoryId: Int): BookListResponse {
        return service.getRecommendBookList(ApiKey.apiKey, categoryId)
    }

    override suspend fun getBestSellerBookList(categoryId: Int): BookListResponse {
        return service.getBestSellerBookList(ApiKey.apiKey, categoryId)
    }

}