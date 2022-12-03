package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetAllRecommendBookListUseCase(
    private val bookRepository: BookRepository
) {
    fun execute(): Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.getAllRecommendBookList()
    }
}