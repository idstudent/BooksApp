package com.example.booksapp.repository

import com.example.booksapp.api.ApiService
import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksTitle
import com.example.booksapp.api.model.Header
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository(
    private val service : ApiService
) {
    fun getNewBooksList(categoryId : Int) : Flow<List<Books>> {
        val booksList = mutableListOf<Books>()


        return flow {
            val response = service.getNewBooks("6D541D537528F0195E926F03541817D36E41219FD869A31FA9EAD136220ABE49", categoryId).mapper()

            if(categoryId == 100) {
                booksList.add(Header())
                booksList.add(BooksTitle("국내 도서"))
            }
            else booksList.add(BooksTitle("외국 도서"))

            booksList.addAll(response)

            emit(booksList)
        }
    }
}