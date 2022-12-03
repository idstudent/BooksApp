package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.usecase.GetAllNewBookListUseCase
import com.example.booksapp.domain.usecase.GetAllRecommendBookListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

class AllBookListViewModel(
    private val getAllNewBookListUseCase: GetAllNewBookListUseCase,
    private val getAllRecommendBookListUseCase: GetAllRecommendBookListUseCase
) : ViewModel() {

    fun getAllNewBookList(categoryId : Int): Flow<List<BooksModel.Response.BooksItem>> {
        return getAllNewBookListUseCase.execute(categoryId)
            .catch { it.printStackTrace() }
    }

    fun getAllRecommendBookList(): Flow<List<BooksModel.Response.BooksItem>> {
        return getAllRecommendBookListUseCase.execute()
            .catch { it.printStackTrace() }
    }
}