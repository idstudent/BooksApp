package com.example.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.repository.BookRepository

class BookSearchViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {

    fun getSearchBooks(query : String, queryType : String, searchType : String) =
        bookRepository.getSearchBooks(query, queryType, searchType)
}