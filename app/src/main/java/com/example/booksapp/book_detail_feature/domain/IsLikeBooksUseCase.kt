package com.example.booksapp.book_detail_feature.domain

import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow

interface IsLikeBooksUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Boolean>>
    data class Params(val bookId: Int)
}