package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow

class LocalBookReportUseCase(
    private val bookRepository: BookRepository
) {
    fun execute() : Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.selectBookReport()
    }
    suspend fun executeInsert(book : BooksModel.Response.BooksItem) {
        return bookRepository.insertBookReport(book)
    }
}