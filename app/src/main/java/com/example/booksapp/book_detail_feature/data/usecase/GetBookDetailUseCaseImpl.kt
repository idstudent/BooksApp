package com.example.booksapp.book_detail_feature.data.usecase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.booksapp.book_detail_feature.domain.GetBookDetailRepository
import com.example.booksapp.book_detail_feature.domain.GetBookDetailUseCase
import com.example.booksapp.core.data.mapper.toBook
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetBookDetailUseCaseImpl @Inject constructor(
    private val getBookDetailRepository: GetBookDetailRepository
): GetBookDetailUseCase{
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun invoke(isbn: String, searchType: String): Flow<ResultData<List<Book>>> {
        return flow {
            try {
                emit(ResultData.Loading)

                val bookDetailInfo = getBookDetailRepository.getBookDetailInfo(isbn, searchType).toBook(bookType = searchType)
                emit(ResultData.Success(bookDetailInfo))
            }catch (e: HttpException) {
                emit(ResultData.Failure(e))
            } catch (e: IOException) {
                emit(ResultData.Failure(e))
            }
        }

    }
}