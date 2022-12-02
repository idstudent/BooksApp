package com.example.booksapp.data.repository.dataSourceImpl

import com.example.booksapp.api.ApiService
import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksTitle
import com.example.booksapp.api.model.Header
import com.example.booksapp.constants.BookFilterType
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRemoteDateSourceImpl(
    private val apiService: ApiService
) : BookRemoteDataSource{
    private val apiKey = "6D541D537528F0195E926F03541817D36E41219FD869A31FA9EAD136220ABE49"

    override suspend fun getNewBookList(categoryId: Int): Flow<List<Books>> {
        val booksList = mutableListOf<Books>()

        return flow {
            val response = apiService.getNewBooks(apiKey, categoryId).mapper()

            if (categoryId == 100) {
                booksList.add(Header())
                booksList.add(BooksTitle( BookFilterType.NEW.name, "100","국내 도서"))
            } else {
                booksList.add(BooksTitle(BookFilterType.NEW.name,"200", "외국 도서"))
            }

            booksList.addAll(response)

            emit(booksList)
        }
    }
}