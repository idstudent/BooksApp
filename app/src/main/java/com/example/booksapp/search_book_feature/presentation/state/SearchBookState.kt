package com.example.booksapp.search_book_feature.presentation.state

import androidx.paging.PagingData
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SearchBookState(
    val searchResultBook: Flow<PagingData<Book>> = emptyFlow(),
    val error: String = "",
    val isLoading: Boolean = false
)
