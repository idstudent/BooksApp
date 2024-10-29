package com.example.booksapp.books_feature.data.source

import com.example.booksapp.books_feature.domain.GetBookRemoteDataSource
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.data.api.ApiService
import javax.inject.Inject

class GetBookRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
): GetBookRemoteDataSource {
    private val apiKey = "ABE29F010BE85A911E51179B8768B517136430738AC2CB0734A98DC5557826F2"

    override suspend fun getBookList(categoryId: Int): BookListResponse {
        return service.getBookList(apiKey, categoryId)
    }

    override suspend fun getRecommendBookList(categoryId: Int): BookListResponse {
        return service.getRecommendBookList(apiKey, categoryId)
    }

}