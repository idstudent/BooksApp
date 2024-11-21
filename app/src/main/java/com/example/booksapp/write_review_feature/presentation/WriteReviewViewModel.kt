package com.example.booksapp.write_review_feature.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.book_detail_feature.presentation.state.BookDetailState
import com.example.booksapp.core.domain.AddBookUseCase
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WriteReviewViewModel @Inject constructor(
    private val addBookUseCase: AddBookUseCase
): ViewModel(){
    var uiState by mutableStateOf(BookDetailState())
        private set

    fun writeReview(book: Book) {
        viewModelScope.launch {
            addBookUseCase.invoke(params = AddBookUseCase.Params(
                book = book
            )).collectLatest { result ->
                when(result) {
                    is ResultData.Success -> {
                        uiState = uiState.copy(isReviewSuccess = true)
                    }

                    is ResultData.Failure -> {
                        Log.e("ljy", "write review error")
                    }

                    is ResultData.Loading -> {}
                }
            }
        }
    }
}