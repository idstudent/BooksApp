package com.example.booksapp.domain.usecase

import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllRecommendBookListUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    fun execute(): Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.getAllRecommendBookList()
    }
}