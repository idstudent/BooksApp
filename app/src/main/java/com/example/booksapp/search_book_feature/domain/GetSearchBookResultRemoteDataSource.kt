package com.example.booksapp.search_book_feature.domain

import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.search_book_feature.data.source.BookPagingSource

interface GetSearchBookResultRemoteDataSource {
    fun getBookPagingSource(title: String, filter: String, searchType: String): BookPagingSource
    suspend fun getSearchBookList(page: Int, title: String, filter: String, searchType: String): BookListResponse
}