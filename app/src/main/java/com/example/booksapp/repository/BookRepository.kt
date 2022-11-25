package com.example.booksapp.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksapp.api.ApiService
import com.example.booksapp.api.model.*
import com.example.booksapp.constants.BookFilterType
import com.example.booksapp.repository.datasource.SearchBooksDataPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BookRepository(
    private val service: ApiService
) {
    private val apiKey = "apiKey"


    fun getNewBooksList(categoryId: Int): Flow<List<Books>> {
        val booksList = mutableListOf<Books>()

        return flow {
            val response = service.getNewBooks(apiKey, categoryId).mapper()

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

    fun getRecommendList(): Flow<List<Books>> {
        val bookList = mutableListOf<Books>()

        return flow {
            bookList.add((BooksTitle(BookFilterType.RECOMMEND.name,"100", "국내 도서 추천")))
            val response = service.getRecommendBooks(apiKey, 100).mapper()

            bookList.addAll(response)

            emit(bookList)
        }
    }

    fun getBestSellerList(categoryId : Int): Flow<List<BooksModel.Response.BooksItem?>> {
        return flow {
            val response = service.getBestSellerBooks(apiKey, categoryId)

            emit(response.item)
        }
    }

    fun getNewBookDetailList(categoryId: Int): Flow<List<BooksModel.Response.BooksItem?>> {
        return flow {
            val response = service.getNewBooks(apiKey, categoryId)

            emit(response.item)
        }
    }

    fun getRecommendBookDetailList(): Flow<List<BooksModel.Response.BooksItem?>> {
        return flow {
            val response = service.getRecommendBooks(apiKey, 100)

            emit(response.item)
        }
    }

    fun getBookDetailInfo(isbn: String, queryType : String, searchType : String): Flow<List<BooksModel.Response.BooksItem?>> {
        return flow {
            val response = service.getSearchBook(apiKey, isbn, queryType, searchType)

            emit(response.item)
        }
    }

    fun getSearchBooks(query : String, queryType : String, searchType : String) : Flow<PagingData<BooksModel.Response.BooksItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchBooksDataPagingSource(service, apiKey, query, queryType, searchType) }
        ).flow
    }
}