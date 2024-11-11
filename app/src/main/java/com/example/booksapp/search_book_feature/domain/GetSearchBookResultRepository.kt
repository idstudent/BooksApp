package com.example.booksapp.search_book_feature.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface GetSearchBookResultRepository {
    fun getSearchBookList(
        pagingConfig: PagingConfig,
        title: String,
        filter: String,
        searchType: String
    ): Flow<PagingData<Book>>
}