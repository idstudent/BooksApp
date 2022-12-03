package com.example.booksapp.domain.usecase

import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.domain.repository.BookRepositoryT
import kotlinx.coroutines.flow.Flow

class GetBestSellerBookListUseCase (
    private val bookRepository: BookRepositoryT
) {
    fun execute(categoryId : Int) : Flow<List<BooksModel.Response.BooksItem>> {
        return bookRepository.getBestSellerList(categoryId)
    }
}