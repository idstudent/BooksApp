package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.Books
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetRecommendBookListUseCase(
    private val bookRepository: BookRepository
) {
    fun execute() : Flow<List<Books>> {
        return bookRepository.getRecommendBookList()
    }
}