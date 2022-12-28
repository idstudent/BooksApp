package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.domain.usecase.GetBestSellerBookListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class BestSellerViewModel @Inject constructor(
    private val getBestSellerBookListUseCase: GetBestSellerBookListUseCase
) : ViewModel() {
    fun getBestSellerBookLIst(categoryId: Int) =
        getBestSellerBookListUseCase.execute(categoryId)
            .catch { it.printStackTrace() }
}