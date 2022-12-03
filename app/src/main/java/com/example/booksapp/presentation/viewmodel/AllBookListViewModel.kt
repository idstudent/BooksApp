package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.domain.usecase.GetAllNewBookListUseCase
import com.example.booksapp.domain.usecase.GetRecommendBookListUseCase
import kotlinx.coroutines.flow.catch

class AllBookListViewModel(
    private val getAllNewBookListUseCase: GetAllNewBookListUseCase,
    private val getRecommendBookListUseCase: GetRecommendBookListUseCase
) : ViewModel() {

    fun getAllNewBookList(categoryId : Int) =
        getAllNewBookListUseCase.execute(categoryId)
            .catch { it.printStackTrace() }

    fun getRecommendBookDetailList() =
        getRecommendBookListUseCase.execute()
            .catch { it.printStackTrace() }
}