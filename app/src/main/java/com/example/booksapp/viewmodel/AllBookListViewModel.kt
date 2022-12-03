package com.example.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.repository.BookRepository
import kotlinx.coroutines.flow.catch

class AllBookListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    fun getAllNewBookList(categoryId : Int) =
        bookRepository.getNewBookDetailList(categoryId)
            .catch { it.printStackTrace() }

    fun getAllRecommendBookList() =
        bookRepository.getRecommendBookDetailList()
            .catch { it.printStackTrace() }

}