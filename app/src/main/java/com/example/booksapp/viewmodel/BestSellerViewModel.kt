package com.example.booksapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.repository.BookRepository
import kotlinx.coroutines.flow.catch

class BestSellerViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    fun getBestSellerBookLIst(categoryId : Int) =
        bookRepository.getBestSellerList(categoryId)
            .catch { it.printStackTrace() }
}