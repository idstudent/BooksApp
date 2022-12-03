package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.Books
import com.example.booksapp.domain.repository.BookRepositoryT
import kotlinx.coroutines.flow.Flow

class GetNewBookListUseCase (
    private val bookRepository: BookRepositoryT
) {
    fun execute(categoryId : Int) : Flow<List<Books>> {
        return bookRepository.getNewBookList(categoryId)
    }
}
