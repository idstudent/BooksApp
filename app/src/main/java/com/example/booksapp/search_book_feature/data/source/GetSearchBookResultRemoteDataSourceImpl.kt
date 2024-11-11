package com.example.booksapp.search_book_feature.data.source

import com.example.booksapp.core.data.ApiKey
import com.example.booksapp.core.data.mapper.toBook
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.data.api.ApiService
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultRemoteDataSource
import javax.inject.Inject

class GetSearchBookResultRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
): GetSearchBookResultRemoteDataSource {

    override fun getBookPagingSource(
        title: String,
        filter: String,
        searchType: String
    ): BookPagingSource {
        return BookPagingSource(this, title, filter, searchType)
    }

    override suspend fun getSearchBookList(page: Int, title: String, filter: String, searchType: String): BookListResponse {

        // title-> inputtext, querytype ->  filter, type -> book or foreign
        return service.getSearchBookInfo(ApiKey.apiKey, page = page, title = title, queryType = filter, searchType = searchType)
    }
}