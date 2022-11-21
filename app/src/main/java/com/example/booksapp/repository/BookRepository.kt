package com.example.booksapp.repository

import android.util.Log
import com.example.booksapp.api.ApiService
import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksTitle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository(
    private val service : ApiService
) {
    fun getNewBooksList(categoryId : Int) : Flow<List<Books>> {
        val booksList = mutableListOf<Books>()

        return flow {
            val response = service.getNewBooks("api_key", categoryId).mapper()

            if(categoryId == 100) booksList.add(BooksTitle("새로 나온 국내 도서"))
            else booksList.add(BooksTitle("새로 나온 외국 도서"))

            booksList.addAll(response)

            emit(booksList)
        }
    }
}