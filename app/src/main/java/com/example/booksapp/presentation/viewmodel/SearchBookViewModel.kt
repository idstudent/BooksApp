package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.usecase.GetSearchBookListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(
    private val getSearchBookListUseCase: GetSearchBookListUseCase
) : ViewModel() {

    private val _searchFilter : MutableLiveData<String> = MutableLiveData()
    val searchFilter : MutableLiveData<String> get() = _searchFilter

    fun setFilter(filter : String) {
        _searchFilter.value = filter
    }
    fun getSearchBooks(query : String, queryType : String, searchType : String) : Flow<PagingData<BooksModel.Response.BooksItem>> {
        return getSearchBookListUseCase.execute(query, queryType, searchType)
    }
}