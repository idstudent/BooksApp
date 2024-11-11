package com.example.booksapp.books_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.books_feature.domain.GetBookListByTypeUseCase
import com.example.booksapp.books_feature.domain.GetBookListUseCase
import com.example.booksapp.books_feature.presentation.state.BookListState
import com.example.booksapp.core.uitl.ResultData
import com.example.booksapp.core.uitl.BookFilterType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getBookListUseCase: GetBookListUseCase,
    private val getBookListByTypeUseCase: GetBookListByTypeUseCase
): ViewModel() {
    var uiState by mutableStateOf(BookListState())
        private set
    fun getBookList() {
        viewModelScope.launch {
            getBookListUseCase.invoke().collectLatest { result ->
                when(result) {
                    is ResultData.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            localBooks = result.data?.first,
                            foreignBooks = result.data?.second,
                            recommendedBooks = result.data?.third
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

    fun getBookListByType(type: BookFilterType) {
        viewModelScope.launch {
            getBookListByTypeUseCase.invoke(type).collectLatest { result ->
                when(result) {
                    is ResultData.Success -> {
                        uiState = when(type) {
                            BookFilterType.LOCAL -> uiState.copy(
                                isLoading = false,
                                localBooks = result.data
                            )
                            BookFilterType.GLOBAL -> uiState.copy(
                                isLoading = false,
                                foreignBooks = result.data
                            )
                            else -> uiState.copy(
                                isLoading = false,
                                recommendedBooks = result.data
                            )
                        }
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