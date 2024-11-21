package com.example.booksapp.search_book_feature.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.domain.model.BookListResponse
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultRemoteDataSource
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchBookResultRepositoryImpl @Inject constructor(
    private val getSearchBookResultDataSource: GetSearchBookResultRemoteDataSource
): GetSearchBookResultRepository {
    override fun getSearchBookList(
        pagingConfig: PagingConfig,
        title: String,
        filter: String,
        searchType: String
    ): Flow<PagingData<Book>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = {
                getSearchBookResultDataSource.getBookPagingSource(title ,filter, searchType)
            }
        ).flow
    }

}