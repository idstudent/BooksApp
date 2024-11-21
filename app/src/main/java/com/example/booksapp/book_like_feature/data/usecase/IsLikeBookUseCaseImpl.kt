package com.example.booksapp.book_like_feature.data.usecase

import com.example.booksapp.core.domain.BookInfoRepository
import com.example.booksapp.book_like_feature.domain.IsLikeBookUseCase
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsLikeBookUseCaseImpl @Inject constructor(
    private val bookRepository: BookInfoRepository
): IsLikeBookUseCase {
    override suspend fun invoke(params: IsLikeBookUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            val isLike = bookRepository.isLike(params.bookId)
            emit(ResultData.Success(isLike))
        }
    }
}