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

    fun getBestSellerBookLIst(categoryId : Int) =
        bookRepository.getBestSellerList(categoryId)
            .catch { it.printStackTrace() }

    fun getNewBookDetailList(categoryId : Int) =
        bookRepository.getNewBookDetailList(categoryId)
            .catch { it.printStackTrace() }

    fun getRecommendBookDetailList() =
        bookRepository.getRecommendBookDetailList()
            .catch { it.printStackTrace() }
}