package com.example.booksapp.data.repository.dataSource

import com.example.booksapp.api.model.Books
import kotlinx.coroutines.flow.Flow

interface BookRemoteDataSource {
    fun getNewBookList(categoryId : Int) : Flow<List<Books>>
    fun getRecommendBookList() : Flow<List<Books>>
}