package com.example.booksapp.book_like_feature.domain

import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow

interface IsLikeBookUseCase {
    suspend operator fun invoke(params: Params): Flow<ResultData<Boolean>>
    data class Params(val bookId: Int)
}