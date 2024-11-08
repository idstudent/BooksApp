package com.example.booksapp.best_seller_book_feature.domain

import com.example.booksapp.core.domain.model.BookListResponse

interface GetBestSellerBookListRepository {
    suspend fun getBestSellerBookList(categoryId: Int): BookListResponse
}