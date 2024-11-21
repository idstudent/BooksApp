package com.example.booksapp.books_feature.domain

import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow

interface GetBookListUseCase {
    operator fun invoke(): Flow<ResultData<Triple<List<Book>, List<Book>, List<Book>>>>

}