package com.example.booksapp.domain.repository

import com.example.booksapp.api.model.Books
import kotlinx.coroutines.flow.Flow

interface BookRepositoryT {
    suspend fun getNewBookList(categoryId : Int) : Flow<List<Books>>
}