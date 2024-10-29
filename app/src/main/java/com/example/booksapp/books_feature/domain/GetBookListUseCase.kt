package com.example.booksapp.books_feature.domain

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.booksapp.books_feature.data.mapper.toBook
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

interface GetBookListUseCase {
    operator fun invoke(): Flow<ResultData<Triple<List<Book>, List<Book>, List<Book>>>>
}
class GetBookListUseCaseImpl @Inject constructor(
    private val getBookListRepository: GetBookListRepository
): GetBookListUseCase {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun invoke(): Flow<ResultData<Triple<List<Book>, List<Book>, List<Book>>>> {
        return flow {
            try {
                emit(ResultData.Loading)

                val localBooks = getBookListRepository.getBookList(100).toBook()
                val foreignBooks = getBookListRepository.getBookList(200).toBook()
                val recommendBooks = getBookListRepository.getRecommendBookList(100).toBook()

                emit(ResultData.Success(Triple(
                    first = localBooks,
                    second = foreignBooks,
                    third = recommendBooks
                )))
            } catch (e: HttpException) {
                emit(ResultData.Failure(e))
            } catch (e: IOException) {
                emit(ResultData.Failure(e))
            }
        }
    }
}