package com.example.booksapp.domain.usecase

import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalBookMarkUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    fun execute() : Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.selectBook()
    }

    suspend fun executeInsert(book : BooksModel.Response.BooksItem)  {
        return bookRepository.insertBook(book)
    }

    suspend fun executeDelete(book : BooksModel.Response.BooksItem)  {
        return bookRepository.deleteBook(book)
    }
}