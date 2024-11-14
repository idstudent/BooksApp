package com.example.booksapp.book_detail_feature.domain

import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow

interface AddBookListUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Unit>>
    data class Params(val book: Book)
}