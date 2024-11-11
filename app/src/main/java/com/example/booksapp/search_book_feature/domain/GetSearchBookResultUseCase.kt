package com.example.booksapp.search_book_feature.domain

import androidx.paging.PagingData
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow

interface GetSearchBookResultUseCase {
    operator fun invoke(title: String, filter: String, searchType: String): Flow<PagingData<Book>>
}