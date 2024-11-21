package com.example.booksapp.review_book_list_feature.data

import com.example.booksapp.core.domain.BookInfoRepository
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import com.example.booksapp.review_book_list_feature.domain.GetReviewBookListUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetReviewBookListUseCaseImpl @Inject constructor(
    private val bookInfoRepository: BookInfoRepository
): GetReviewBookListUseCase{
    override suspend fun invoke(): Flow<List<Book>> {
        return bookInfoRepository.getReviewBookList()
    }

}