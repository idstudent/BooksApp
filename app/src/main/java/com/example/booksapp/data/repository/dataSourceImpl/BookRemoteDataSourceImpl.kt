package com.example.booksapp.data.repository.dataSourceImpl

import com.example.booksapp.api.ApiService
import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.api.model.BooksTitle
import com.example.booksapp.api.model.Header
import com.example.booksapp.constants.BookFilterType
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRemoteDataSourceImpl(
    private val apiService: ApiService
) : BookRemoteDataSource{
    private val apiKey = "api_key"

    override fun getNewBookList(categoryId: Int): Flow<List<Books>> {
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

    override fun getRecommendBookList(): Flow<List<Books>> {
        val bookList = mutableListOf<Books>()

        return flow {
            bookList.add((BooksTitle(BookFilterType.RECOMMEND.name,"100", "국내 도서 추천")))
            val response = apiService.getRecommendBooks(apiKey, 100).mapper()

            bookList.addAll(response)

            emit(bookList)
        }
    }
    override fun getBestSellerList(categoryId : Int): Flow<List<BooksModel.Response.BooksItem>> {
        return flow {
            val response = apiService.getBestSellerBooks(apiKey, categoryId)

            emit(response.item)
        }
    }

    override fun getBookDetailInfo(isbn: String, queryType: String, searchType: String): Flow<List<BooksModel.Response.BooksItem>> {
        return flow {
            val response = apiService.getSearchBook(apiKey, isbn, queryType, searchType)

            emit(response.item)
        }
    }
}