package com.example.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.repository.BookRepository
import kotlinx.coroutines.flow.catch

class BooksViewModel(
    private val bookRepository: BookRepository
) : ViewModel(){
    fun getNewBookList(categoryId : Int) =
        bookRepository.getNewBooksList(categoryId)
            .catch { it.printStackTrace() }

    fun getRecommendBookList() =
        bookRepository.getRecommendList()
            .catch { it.printStackTrace() }

    fun getBestSellerBookLIst(id : Int) =
        bookRepository.getBestSellerList(id)
            .catch { it.printStackTrace() }
}