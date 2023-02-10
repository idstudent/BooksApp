package com.example.booksapp.domain.usecase

import androidx.paging.PagingData
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchBookListUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    fun execute(query : String, queryType : String, searchType : String): Flow<PagingData<BooksModel.Response.BooksItem>> {
        return bookRepository.getSearchBooks(query, queryType, searchType)
    }
}