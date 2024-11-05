package com.example.booksapp.book_detail_feature.domain

import com.example.booksapp.core.domain.model.BookListResponse

interface GetBookDetailRemoteDataSource {
    suspend fun getBookDetailInfo(isbn: String, searchType: String): BookListResponse
}