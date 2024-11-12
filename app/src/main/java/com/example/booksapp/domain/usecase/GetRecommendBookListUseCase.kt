package com.example.booksapp.domain.usecase

import com.example.booksapp.core.data.remote.model.Books
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecommendBookListUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    fun execute() : Flow<List<Books>> {
        return bookRepository.getRecommendBookList()
    }
}