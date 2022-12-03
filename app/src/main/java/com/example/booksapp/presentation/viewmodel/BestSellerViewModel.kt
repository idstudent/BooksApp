package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.domain.usecase.GetBestSellerBookListUseCase
import kotlinx.coroutines.flow.catch

class BestSellerViewModel(
    private val getBestSellerBookListUseCase: GetBestSellerBookListUseCase
) : ViewModel() {
    fun getBestSellerBookLIst(categoryId: Int) =
        getBestSellerBookListUseCase.execute(categoryId)
            .catch { it.printStackTrace() }
}