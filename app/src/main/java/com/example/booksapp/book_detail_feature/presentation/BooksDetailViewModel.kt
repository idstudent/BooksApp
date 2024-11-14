package com.example.booksapp.book_detail_feature.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.book_detail_feature.domain.AddBookListUseCase
import com.example.booksapp.book_detail_feature.domain.DeleteBookLikeUseCase
import com.example.booksapp.book_detail_feature.domain.GetBookDetailUseCase
import com.example.booksapp.book_detail_feature.domain.IsLikeBooksUseCase
import com.example.booksapp.book_detail_feature.presentation.state.BookDetailState
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetailUseCase: GetBookDetailUseCase,
    private val addBookLikeUseCase: AddBookListUseCase,
    private val deleteBookLikeUseCase: DeleteBookLikeUseCase,
    private val isLikeBookUseCase: IsLikeBooksUseCase
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

    fun getIsLike(getIsLike: BookDetailEvent.IsLikeBook) {
        event(getIsLike)
    }
    fun setLikeBook(book: Book) {
        if(!uiState.isLike) {
            event(BookDetailEvent.AddLikeBook(book))
        }else {
            event(BookDetailEvent.DeleteLikeBook(book))
        }
    }

    private fun event(event: BookDetailEvent) {
        when(event) {
            is BookDetailEvent.AddLikeBook -> {
                viewModelScope.launch {
                    addBookLikeUseCase.invoke(params = AddBookListUseCase.Params(
                        book = event.book
                    )).collectLatest { result ->
                        when(result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(isLike = true)
                            }

                            is ResultData.Failure -> {
                                Log.e("ljy", "add like error")
                            }

                            is ResultData.Loading -> {}
                        }
                    }
                }
            }

            is BookDetailEvent.DeleteLikeBook -> {
                viewModelScope.launch {
                    deleteBookLikeUseCase.invoke(params = DeleteBookLikeUseCase.Params(
                        book = event.book
                    )).collectLatest { result ->
                        when(result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(isLike = false)
                            }

                            is ResultData.Failure -> {
                                Log.e("ljy", "delete like error")
                            }

                            is ResultData.Loading -> {}
                        }
                    }
                }
            }

            is BookDetailEvent.IsLikeBook -> {
                viewModelScope.launch {
                    isLikeBookUseCase.invoke(params = IsLikeBooksUseCase.Params(
                        bookId = event.bookId
                    )).collectLatest { result ->
                        when(result) {
                            is ResultData.Success -> {
                                uiState = uiState.copy(isLike = result.data == true)
                            }
                            is ResultData.Failure -> {
                                Log.e("ljy", "get like error")
                            }

                            is ResultData.Loading -> {}
                        }
                    }
                }
            }
        }
    }
}