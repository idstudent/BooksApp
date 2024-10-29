package com.example.booksapp.books_feature.domain

import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksapp.core.domain.model.BookItem
import com.example.booksapp.core.domain.model.BookListResponse
import kotlinx.coroutines.flow.Flow

interface GetBookListRepository {
    suspend fun getBookList(categoryId: Int): BookListResponse
    suspend fun getRecommendBookList(categoryId: Int): BookListResponse
}