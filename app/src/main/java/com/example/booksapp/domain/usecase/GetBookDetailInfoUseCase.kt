package com.example.booksapp.domain.usecase

import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookDetailInfoUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    fun execute(isbn : String, queryType : String, searchType : String) : Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.getBookDetailInfo(isbn, queryType, searchType)
    }
}