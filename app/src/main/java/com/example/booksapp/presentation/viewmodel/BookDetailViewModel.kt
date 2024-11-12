package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.domain.usecase.GetBookDetailInfoUseCase
import com.example.booksapp.domain.usecase.LocalBookMarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookDetailViewModel @Inject constructor(
    private val getBookDetailInfoUseCase: GetBookDetailInfoUseCase,
    private val localBookMarkUseCase: LocalBookMarkUseCase
) : ViewModel() {
    fun getBookDetailInfo(isbn: String, queryType: String, searchType: String) =
        getBookDetailInfoUseCase.execute(isbn, queryType, searchType)
            .catch { it.printStackTrace() }

    fun selectBook(): Flow<List<BooksModel.Response.BooksItem>> {
         return localBookMarkUseCase.execute()
    }

    fun insertBook(book: BooksModel.Response.BooksItem) {
        viewModelScope.launch {
            localBookMarkUseCase.executeInsert(book)
        }
    }

    fun deleteBook(book: BooksModel.Response.BooksItem) {
        viewModelScope.launch {
            localBookMarkUseCase.executeDelete(book)
        }
    }
}