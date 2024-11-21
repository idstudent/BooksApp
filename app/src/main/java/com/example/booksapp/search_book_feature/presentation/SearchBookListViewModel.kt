package com.example.booksapp.search_book_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.core.uitl.ResultData
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultUseCase
import com.example.booksapp.search_book_feature.presentation.state.SearchBookState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class SearchBookListViewModel @Inject constructor(
    private val getSearchBookResultUseCase: GetSearchBookResultUseCase
): ViewModel() {
    var uiState by mutableStateOf(SearchBookState())
        private set

    fun getSearchBookList(title: String, filter: String, searchType: String) {
        viewModelScope.launch {
            val searchResults = getSearchBookResultUseCase.invoke(title, filter, searchType)
                .cachedIn(viewModelScope)

            uiState = uiState.copy(searchResultBook = searchResults)
        }
    }
}
