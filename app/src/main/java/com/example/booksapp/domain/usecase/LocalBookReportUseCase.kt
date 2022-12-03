package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepositoryT
import kotlinx.coroutines.flow.Flow

class LocalBookReportUseCase(
    private val bookRepository: BookRepositoryT
) {
    fun execute() : Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.selectBookReport()
    }
    suspend fun executeInsert(book : BooksModel.Response.BooksItem) {
        return bookRepository.insertBookReport(book)
    }
}