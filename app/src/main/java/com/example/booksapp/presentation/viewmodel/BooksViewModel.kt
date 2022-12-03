package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.domain.usecase.GetNewBookListUseCase
import com.example.booksapp.domain.usecase.GetRecommendBookListUseCase
import com.example.booksapp.repository.BookRepository
import kotlinx.coroutines.flow.catch

class BooksViewModel(
    private val bookRepository: BookRepository,
    private val getNewBookListUseCase: GetNewBookListUseCase,
    private val getRecommendBookListUseCase: GetRecommendBookListUseCase
) : ViewModel(){
    fun getNewBookList(categoryId : Int) =
        getNewBookListUseCase.execute(categoryId)
            .catch { it.printStackTrace() }

    fun getRecommendBookList() =
        getRecommendBookListUseCase.execute()
            .catch { it.printStackTrace() }




    fun getSearchBooks(query : String, queryType : String, searchType : String) =
        bookRepository.getSearchBooks(query, queryType, searchType)
}