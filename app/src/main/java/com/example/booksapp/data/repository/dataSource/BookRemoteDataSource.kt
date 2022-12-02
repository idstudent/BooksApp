package com.example.booksapp.data.repository.dataSource

import com.example.booksapp.api.model.Books
import kotlinx.coroutines.flow.Flow

interface BookRemoteDataSource {
    suspend fun getNewBookList(categoryId : Int) : Flow<List<Books>>
}