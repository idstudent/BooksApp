package com.example.booksapp.books_feature.data.usecase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.booksapp.core.data.mapper.toBook
import com.example.booksapp.books_feature.domain.GetBookListByTypeUseCase
import com.example.booksapp.books_feature.domain.GetBookListRepository
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import com.example.booksapp.data.constants.BookFilterType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBookListByTypeUseCaseImpl @Inject constructor(
    private val getBookListRepository: GetBookListRepository
): GetBookListByTypeUseCase {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun invoke(type: BookFilterType): Flow<ResultData<List<Book>>> {
        return flow {
            try {
                emit(ResultData.Loading)

                val book = when(type) {
                    BookFilterType.LOCAL -> getBookListRepository.getBookList(100).toBook()
                    BookFilterType.GLOBAL -> getBookListRepository.getBookList(200).toBook()
                    BookFilterType.RECOMMEND -> getBookListRepository.getRecommendBookList(100).toBook()
                    BookFilterType.BEST_LOCAL -> getBookListRepository.getBestSellerBookList(100).toBook()
                    BookFilterType.BEST_GLOBAL -> getBookListRepository.getBestSellerBookList(200).toBook()
                    BookFilterType.NEW -> getBookListRepository.getBestSellerBookList(200).toBook()
                }

                emit(ResultData.Success(book))

            } catch (e: HttpException) {
                emit(ResultData.Failure(e))
            } catch (e: IOException) {
                emit(ResultData.Failure(e))
            }
        }
    }
}