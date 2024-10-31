package com.example.booksapp.books_feature.domain

import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import com.example.booksapp.data.constants.BookFilterType
import kotlinx.coroutines.flow.Flow

interface GetBookListByTypeUseCase {
    operator fun invoke(type: BookFilterType) : Flow<ResultData<List<Book>>>
}