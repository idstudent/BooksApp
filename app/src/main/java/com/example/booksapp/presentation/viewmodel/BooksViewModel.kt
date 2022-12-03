package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.domain.usecase.GetNewBookListUseCase
import com.example.booksapp.domain.usecase.GetRecommendBookListUseCase
import com.example.booksapp.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

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



    fun getNewBookDetailList(categoryId : Int) =
        bookRepository.getNewBookDetailList(categoryId)
            .catch { it.printStackTrace() }

    fun getRecommendBookDetailList() =
        bookRepository.getRecommendBookDetailList()
            .catch { it.printStackTrace() }

    fun getSearchBooks(query : String, queryType : String, searchType : String) =
        bookRepository.getSearchBooks(query, queryType, searchType)
}