package com.example.booksapp.book_like_feature.data.usecase

import com.example.booksapp.book_like_feature.domain.BookLikeRepository
import com.example.booksapp.book_like_feature.domain.GetLikeBookListUseCase
import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLikeBookListUseCaseImpl @Inject constructor(
    private val bookRepository: BookLikeRepository
): GetLikeBookListUseCase{
    override suspend fun invoke(): Flow<List<Book>>{
        return bookRepository.getBookList()
    }
}