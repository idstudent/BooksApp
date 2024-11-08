package com.example.booksapp.best_seller_book_feature.data.usecase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListRepository
import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListUseCase
import com.example.booksapp.core.data.mapper.toBook
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBestSellerBookListUseCaseImpl @Inject constructor(
    private val getBestSellerBookListRepository: GetBestSellerBookListRepository
): GetBestSellerBookListUseCase {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun invoke(categoryId: Int): Flow<ResultData<List<Book>>> {
        return flow {
            try {
                emit(ResultData.Loading)
                val result = getBestSellerBookListRepository.getBestSellerBookList(categoryId).toBook()

                emit(ResultData.Success(result))
            } catch (e: HttpException) {
                emit(ResultData.Failure(e))
            } catch (e: IOException) {
                emit(ResultData.Failure(e))
            }
        }

    }

}