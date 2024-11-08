package com.example.booksapp.best_seller_book_feature.domain

import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow

interface GetBestSellerBookListUseCase {
    operator fun invoke(categoryId: Int): Flow<ResultData<List<Book>>>
}