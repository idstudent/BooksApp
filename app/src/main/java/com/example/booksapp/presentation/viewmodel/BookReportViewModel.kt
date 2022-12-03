package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.usecase.LocalBookReportUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BookReportViewModel(
    private val localBookReportUseCase: LocalBookReportUseCase
) : ViewModel() {
    fun selectBookReport(): Flow<List<BooksModel.Response.BooksItem>> {
        return localBookReportUseCase.execute()
    }

    fun insertReport(book: BooksModel.Response.BooksItem) {
        viewModelScope.launch {
            localBookReportUseCase.executeInsert(book)
        }
    }
}