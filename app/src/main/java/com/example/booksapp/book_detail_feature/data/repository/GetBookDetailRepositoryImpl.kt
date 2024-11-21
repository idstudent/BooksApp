package com.example.booksapp.book_detail_feature.data.repository

import com.example.booksapp.book_detail_feature.domain.GetBookDetailRemoteDataSource
import com.example.booksapp.book_detail_feature.domain.GetBookDetailRepository
import com.example.booksapp.core.domain.model.BookListResponse
import javax.inject.Inject

class GetBookDetailRepositoryImpl @Inject constructor(
    private val remoteDataSource: GetBookDetailRemoteDataSource
): GetBookDetailRepository {
    override suspend fun getBookDetailInfo(
        isbn: String,
        searchType: String
    ): BookListResponse {
        return remoteDataSource.getBookDetailInfo(isbn, searchType)
    }
}