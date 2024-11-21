package com.example.booksapp.review_book_list_feature.domain

import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

interface GetReviewBookListUseCase {
    suspend operator fun invoke(): Flow<List<Book>>
}