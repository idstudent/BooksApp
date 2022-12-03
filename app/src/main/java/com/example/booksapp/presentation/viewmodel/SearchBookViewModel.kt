package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.usecase.GetSearchBookListUseCase
import kotlinx.coroutines.flow.Flow

class SearchBookViewModel(
    private val getSearchBookListUseCase: GetSearchBookListUseCase
) : ViewModel() {
    fun getSearchBooks(query : String, queryType : String, searchType : String) : Flow<PagingData<BooksModel.Response.BooksItem>> {
        return getSearchBookListUseCase.execute(query, queryType, searchType)
    }
}