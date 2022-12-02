package com.example.booksapp.data.repository

import com.example.booksapp.api.model.Books
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.domain.repository.BookRepositoryT
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(
    private val bookRemoteDataSource: BookRemoteDataSource
) : BookRepositoryT {
    override suspend fun getNewBookList(categoryId: Int) : Flow<List<Books>> {
        return bookRemoteDataSource.getNewBookList(categoryId)
    }
}