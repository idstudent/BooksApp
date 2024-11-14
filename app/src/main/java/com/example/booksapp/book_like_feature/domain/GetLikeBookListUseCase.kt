package com.example.booksapp.book_like_feature.domain

import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface GetLikeBookListUseCase {
    suspend operator fun invoke(): Flow<List<Book>>
}