package com.example.booksapp.domain.usecase

import com.example.booksapp.data.api.model.Books
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewBookListUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    fun execute(categoryId : Int) : Flow<List<Books>> {
        return bookRepository.getNewBookList(categoryId)
    }
}
