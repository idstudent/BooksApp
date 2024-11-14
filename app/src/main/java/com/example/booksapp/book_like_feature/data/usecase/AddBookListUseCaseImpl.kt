package com.example.booksapp.book_like_feature.data.usecase

import com.example.booksapp.book_like_feature.domain.AddBookListUseCase
import com.example.booksapp.book_like_feature.domain.BookLikeRepository
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddBookListUseCaseImpl @Inject constructor(
    private val bookRepository: BookLikeRepository
): AddBookListUseCase {
    override suspend fun invoke(params: AddBookListUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val insert = bookRepository.insert(params.book)
            emit(ResultData.Success(insert))
        }
    }
}