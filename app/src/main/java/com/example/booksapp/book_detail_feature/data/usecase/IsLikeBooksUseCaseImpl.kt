package com.example.booksapp.book_detail_feature.data.usecase

import com.example.booksapp.book_detail_feature.domain.BookLikeRepository
import com.example.booksapp.book_detail_feature.domain.IsLikeBooksUseCase
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class IsLikeBooksUseCaseImpl @Inject constructor(
    private val bookRepository: BookLikeRepository
): IsLikeBooksUseCase {
    override suspend fun invoke(params: IsLikeBooksUseCase.Params): Flow<ResultData<Boolean>> {
        return flow {
            val isLike = bookRepository.isLike(params.bookId)
            emit(ResultData.Success(isLike))
        }
    }
}