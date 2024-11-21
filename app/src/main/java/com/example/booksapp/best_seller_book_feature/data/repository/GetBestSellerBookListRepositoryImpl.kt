package com.example.booksapp.best_seller_book_feature.data.repository

import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListRemoteDataSource
import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListRepository
import com.example.booksapp.core.domain.model.BookListResponse
import javax.inject.Inject

class GetBestSellerBookListRepositoryImpl @Inject constructor(
    private val remoteDataSource: GetBestSellerBookListRemoteDataSource
): GetBestSellerBookListRepository {
    override suspend fun getBestSellerBookList(categoryId: Int): BookListResponse {
        return remoteDataSource.getBestSellerBookList(categoryId)
    }
}