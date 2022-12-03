package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.Books
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepositoryT
import kotlinx.coroutines.flow.Flow

class GetRecommendBookListUseCase(
    private val bookRepository: BookRepositoryT
) {
    fun execute() : Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.getAllRecommendBookList()
    }
}