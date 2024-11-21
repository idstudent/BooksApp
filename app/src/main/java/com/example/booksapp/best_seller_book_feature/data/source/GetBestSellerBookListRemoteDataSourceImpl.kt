package com.example.booksapp.best_seller_book_feature.data.source

import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListRemoteDataSource
import com.example.booksapp.core.data.ApiKey
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.core.data.remote.ApiService
import javax.inject.Inject

class GetBestSellerBookListRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
): GetBestSellerBookListRemoteDataSource {
    override suspend fun getBestSellerBookList(categoryId: Int): BookListResponse {
        return service.getBestSellerBookList(ApiKey.apiKey, categoryId)
    }
}