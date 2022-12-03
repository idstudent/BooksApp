package com.example.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class BookDetailViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    fun getBookDetailInfo(isbn : String, queryType : String, searchType : String) =
        bookRepository.getBookDetailInfo(isbn, queryType, searchType)
            .catch { it.printStackTrace() }

    fun selectBook() : Flow<List<BooksModel.Response.BooksItem>> {
        return flow {
            emit(bookRepository.selectBook())
        }
    }

    fun insertBook(book : BooksModel.Response.BooksItem) {
        viewModelScope.launch {
            bookRepository.insertBook(book)
        }
    }

    fun deleteBook(book : BooksModel.Response.BooksItem) {
        viewModelScope.launch {
            bookRepository.deleteBook(book)
        }
    }

    fun selectReport() : Flow<List<BooksModel.Response.BooksItem>> {
        return flow {
            emit(bookRepository.selectReport())
        }
    }

    fun insertReport(book : BooksModel.Response.BooksItem) {
        viewModelScope.launch {
            bookRepository.insertReport(book)
        }
    }

}