package com.example.booksapp.book_detail_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.book_detail_feature.domain.GetBookDetailUseCase
import com.example.booksapp.book_detail_feature.presentation.state.BookDetailState
import com.example.booksapp.books_feature.presentation.state.BookListState
import com.example.booksapp.core.uitl.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase
): ViewModel(){
    var uiState by mutableStateOf(BookDetailState())
        private set

    fun getBookDetailInfo(isbn : String, searchType : String) {
        viewModelScope.launch {
            getBookDetailUseCase.invoke(isbn, searchType).collectLatest { result ->
                when(result) {
                    is ResultData.Success -> {
                        uiState = uiState.copy(
                            bookDetailInfo = result.data,
                            isLoading = false,
                        )
                    }
                    is ResultData.Failure -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            error = result.e?.message.toString()
                        )
                    }

                    is ResultData.Loading -> {
                        uiState = uiState.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}