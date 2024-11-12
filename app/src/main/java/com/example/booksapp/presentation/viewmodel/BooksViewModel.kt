package com.example.booksapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.booksapp.core.data.remote.model.Books
import com.example.booksapp.domain.usecase.GetNewBookListUseCase
import com.example.booksapp.domain.usecase.GetRecommendBookListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class BooksViewModel @Inject constructor(
    private val getNewBookListUseCase: GetNewBookListUseCase,
    private val getRecommendBookListUseCase: GetRecommendBookListUseCase
) : ViewModel(){
    fun getNewBookList(categoryId : Int): Flow<List<Books>> {
        return getNewBookListUseCase.execute(categoryId)
            .catch { it.printStackTrace() }
    }

    fun getRecommendBookList(): Flow<List<Books>> {
        return getRecommendBookListUseCase.execute()
            .catch { it.printStackTrace() }
    }
}