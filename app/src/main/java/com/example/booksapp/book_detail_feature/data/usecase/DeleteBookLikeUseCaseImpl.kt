package com.example.booksapp.book_detail_feature.data.usecase

import com.example.booksapp.book_detail_feature.domain.BookLikeRepository
import com.example.booksapp.book_detail_feature.domain.DeleteBookLikeUseCase
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteBookLikeUseCaseImpl @Inject constructor(
    private val bookRepository: BookLikeRepository
): DeleteBookLikeUseCase {
    override suspend fun invoke(params: DeleteBookLikeUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val delete = bookRepository.delete(params.book)
            emit(ResultData.Success(delete))
        }
    }

}