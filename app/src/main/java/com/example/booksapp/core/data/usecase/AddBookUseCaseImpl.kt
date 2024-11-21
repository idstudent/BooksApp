package com.example.booksapp.core.data.usecase

import com.example.booksapp.core.domain.AddBookUseCase
import com.example.booksapp.core.domain.BookInfoRepository
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AddBookUseCaseImpl @Inject constructor(
    private val bookRepository: BookInfoRepository
): AddBookUseCase {
    override suspend fun invoke(params: AddBookUseCase.Params): Flow<ResultData<Unit>> {
        return flow {
            val insert = bookRepository.insert(params.book)
            emit(ResultData.Success(insert))
        }
    }
}