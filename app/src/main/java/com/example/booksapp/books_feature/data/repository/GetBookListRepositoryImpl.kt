package com.example.booksapp.books_feature.data.repository

import com.example.booksapp.books_feature.domain.GetBookListRepository
import com.example.booksapp.books_feature.domain.GetBookRemoteDataSource
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookListRepositoryImpl @Inject constructor(
    private val remoteDataSource: GetBookRemoteDataSource
): GetBookListRepository{
    override suspend fun getBookList(categoryId: Int): BookListResponse {
        return remoteDataSource.getBookList(categoryId)
    }

    override suspend fun getRecommendBookList(categoryId: Int): BookListResponse {
        return remoteDataSource.getRecommendBookList(categoryId)
    }

    override suspend fun getBestSellerBookList(categoryId: Int): BookListResponse {
        return remoteDataSource.getBestSellerBookList(categoryId)
    }

}