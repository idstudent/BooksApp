package com.example.booksapp.best_seller_book_feature.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListUseCase
import com.example.booksapp.best_seller_book_feature.presentation.state.BestSellerBookListState
import com.example.booksapp.core.uitl.ResultData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BestSellerBookViewModel @Inject constructor(
    private val getBestSellerBookListUseCase: GetBestSellerBookListUseCase
) : ViewModel() {
    var uiState by mutableStateOf(BestSellerBookListState())
        private set

    init {
        getLocalBooks()
    }

    fun getLocalBooks() {

        viewModelScope.launch {
            getBestSellerBookListUseCase.invoke(100).collectLatest { result ->
                when (result) {
                    is ResultData.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            localBooks = result.data
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

    fun getGlobalBooks() {
        viewModelScope.launch {
            getBestSellerBookListUseCase.invoke(200).collectLatest { result ->
                when (result) {
                    is ResultData.Success -> {
                        uiState = uiState.copy(
                            isLoading = false,
                            foreignBooks = result.data
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