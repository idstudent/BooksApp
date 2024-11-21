package com.example.booksapp.books_feature.data.usecase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.booksapp.core.data.mapper.toBook
import com.example.booksapp.books_feature.domain.GetBookListRepository
import com.example.booksapp.books_feature.domain.GetBookListUseCase
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.BookFilterType
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBookListUseCaseImpl @Inject constructor(
    private val getBookListRepository: GetBookListRepository
): GetBookListUseCase {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun invoke(): Flow<ResultData<Triple<List<Book>, List<Book>, List<Book>>>> {
        return flow {
            try {
                emit(ResultData.Loading)

                val localBooks = getBookListRepository.getBookList(100).toBook(bookType = BookFilterType.LOCAL.name)
                val foreignBooks = getBookListRepository.getBookList(200).toBook(bookType = BookFilterType.GLOBAL.name)
                val recommendBooks = getBookListRepository.getRecommendBookList(100).toBook(bookType = BookFilterType.LOCAL.name)

                emit(
                    ResultData.Success(Triple(
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