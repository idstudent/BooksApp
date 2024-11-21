package com.example.booksapp.review_book_list_feature

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.review_book_list_feature.domain.GetReviewBookListUseCase
import com.example.booksapp.review_book_list_feature.presentation.state.ReviewBookListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReviewBookListViewModel @Inject constructor(
    private val getReviewBookListUseCase: GetReviewBookListUseCase
): ViewModel(){
    var uiState by mutableStateOf(ReviewBookListState())
        private set
    init {
        getReviewBooks()
    }

    private fun getReviewBooks() {
        viewModelScope.launch {
            getReviewBookListUseCase.invoke().collectLatest {
                uiState = uiState.copy(books = it)
            }
        }
    }
}