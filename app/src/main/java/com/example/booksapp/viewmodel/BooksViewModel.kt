package com.example.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.repository.BookRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class BooksViewModel(
    private val bookRepository: BookRepository
) : ViewModel(){
    fun getNewBookList(categoryId : Int) =
        bookRepository.getNewBooksList(categoryId)
            .catch { it.printStackTrace() }

    fun getRecommendBookList() =
        bookRepository.getRecommendList()
            .catch { it.printStackTrace() }

}