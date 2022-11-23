package com.example.booksapp.repository

import android.util.Log
import com.example.booksapp.api.ApiService
import com.example.booksapp.api.model.Book
import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksTitle
import com.example.booksapp.api.model.Header
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository(
    private val service: ApiService
) {
    private val apiKey = "api_key"
    fun getNewBooksList(categoryId: Int): Flow<List<Books>> {
        val booksList = mutableListOf<Books>()

        return flow {
            val response = service.getNewBooks(apiKey, categoryId).mapper()

            if (categoryId == 100) {
                booksList.add(Header())
                booksList.add(BooksTitle("국내 도서"))
            } else {
                booksList.add(BooksTitle("외국 도서"))
            }

            booksList.addAll(response)

            emit(booksList)
        }
    }

    fun getRecommendList(): Flow<List<Books>> {
        val bookList = mutableListOf<Books>()

        return flow {

            bookList.add((BooksTitle("국내 도서 추천")))
            val response = service.getRecommendBooks(apiKey, 100).mapper()

            bookList.addAll(response)

            emit(bookList)
        }
    }
}