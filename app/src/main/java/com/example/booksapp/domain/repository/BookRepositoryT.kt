package com.example.booksapp.domain.repository

import com.example.booksapp.api.model.Books
import kotlinx.coroutines.flow.Flow

interface BookRepositoryT {
    fun getNewBookList(categoryId : Int) : Flow<List<Books>>
    fun getRecommendBookList() : Flow<List<Books>>
}