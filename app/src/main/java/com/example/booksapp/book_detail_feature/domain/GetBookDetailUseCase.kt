package com.example.booksapp.book_detail_feature.domain

import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow

interface GetBookDetailUseCase {
    operator fun invoke(isbn : String, searchType : String): Flow<ResultData<List<Book>>>
}