package com.example.booksapp.book_like_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.book_like_feature.domain.GetLikeBookListUseCase
import com.example.booksapp.book_like_feature.presentation.state.BookLikeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookLikeViewModel @Inject constructor(
    private val getLikeBookListUseCase: GetLikeBookListUseCase
): ViewModel(){
    var uiState by mutableStateOf(BookLikeState())
        private set

    init {
        getLikeBooks()
    }

    private fun getLikeBooks() {
        viewModelScope.launch {
            getLikeBookListUseCase.invoke().collectLatest {
                uiState = uiState.copy(books = it)
            }
        }
    }
}