package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.usecase.GetSearchBookListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchBookViewModel @Inject constructor(
    private val getSearchBookListUseCase: GetSearchBookListUseCase
) : ViewModel() {

    private val _searchFilter : MutableStateFlow<String> = MutableStateFlow("title")
    val searchFilter = _searchFilter.asStateFlow()

    fun setFilter(filter : String) {
        viewModelScope.launch {
            _searchFilter.update { filter }
        }
    }
    fun getSearchBooks(query : String, queryType : String, searchType : String) : Flow<PagingData<BooksModel.Response.BooksItem>> {
        return getSearchBookListUseCase.execute(query, queryType, searchType)
    }
}