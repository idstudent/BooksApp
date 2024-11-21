package com.example.booksapp.book_detail_feature.data.source

import android.util.Log
import com.example.booksapp.book_detail_feature.domain.GetBookDetailRemoteDataSource
import com.example.booksapp.core.data.ApiKey
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.core.data.remote.ApiService
import javax.inject.Inject

class GetBookDetailRemoteDataSourceImpl @Inject constructor(
    private val service: ApiService
): GetBookDetailRemoteDataSource {
    override suspend fun getBookDetailInfo(
        isbn: String,
        searchType: String
    ): BookListResponse {
        Log.e("ljy", "$isbn $searchType")
        return service.getSearchBookInfo(ApiKey.apiKey, title = isbn, "isbn", searchType = searchType)
    }
}