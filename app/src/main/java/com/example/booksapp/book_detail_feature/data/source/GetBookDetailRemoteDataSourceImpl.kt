package com.example.booksapp.book_detail_feature.data.source

import com.example.booksapp.book_detail_feature.domain.GetBookDetailRemoteDataSource
import com.example.booksapp.books_feature.domain.GetBookRemoteDataSource
import com.example.booksapp.core.data.ApiKey
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.data.api.ApiService
import javax.inject.Inject

class GetBookDetailRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
): GetBookDetailRemoteDataSource {
    override suspend fun getBookDetailInfo(
        isbn: String,
        searchType: String
    ): BookListResponse {
        return service.getSearchBookInfo(ApiKey.apiKey, title = isbn, "isbn", searchType = searchType)
    }
}