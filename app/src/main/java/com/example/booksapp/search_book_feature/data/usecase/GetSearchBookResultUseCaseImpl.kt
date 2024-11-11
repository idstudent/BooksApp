package com.example.booksapp.search_book_feature.data.usecase

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksapp.core.data.mapper.toBook
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultRepository
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSearchBookResultUseCaseImpl @Inject constructor(
    private val getSearchBookResultRepository: GetSearchBookResultRepository
): GetSearchBookResultUseCase {
    override fun invoke(title: String, filter: String, searchType: String): Flow<PagingData<Book>> {
        return getSearchBookResultRepository.getSearchBookList(
            pagingConfig = PagingConfig(
                pageSize = 20,
                initialLoadSize = 20
            ),
            title,
            filter,
            searchType
        )
    }
}