package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class GetAllNewBookListUseCase(
    private val bookRepository: BookRepository
) {
    fun execute(categoryId: Int): Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.getAllNewBookList(categoryId)
    }
}