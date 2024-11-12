package com.example.booksapp.data.repository.dataSourceImpl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.booksapp.core.data.remote.ApiService
import com.example.booksapp.core.data.remote.model.Books
import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.core.data.remote.model.BooksTitle
import com.example.booksapp.core.data.remote.model.Header
import com.example.booksapp.core.uitl.BookFilterType
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.data.repository.dataSource.SearchBooksDataPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BookRemoteDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : BookRemoteDataSource {
    private val apiKey = "api_key"

    override fun getNewBookList(categoryId: Int): Flow<List<Books>> {
        val booksList = mutableListOf<Books>()

        return flow {
            val response = apiService.getNewBooks(apiKey, categoryId).mapper()

            if (categoryId == 100) {
                booksList.add(Header())
                booksList.add(BooksTitle(BookFilterType.NEW.name, "100", "국내 도서"))
            } else {
                booksList.add(BooksTitle(BookFilterType.NEW.name, "200", "외국 도서"))
            }

            booksList.addAll(response)

            emit(booksList)
        }
    }

    override fun getRecommendBookList(): Flow<List<Books>> {
        val bookList = mutableListOf<Books>()

        return flow {
            bookList.add((BooksTitle(BookFilterType.RECOMMEND.name, "100", "국내 도서 추천")))
            val response = apiService.getRecommendBooks(apiKey, 100).mapper()

            bookList.addAll(response)

            emit(bookList)
        }
    }

    override fun getBestSellerList(categoryId: Int): Flow<List<BooksModel.Response.BooksItem>> {
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

    override fun getSearchBooks(
        query: String,
        queryType: String,
        searchType: String
    ): Flow<PagingData<BooksModel.Response.BooksItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { SearchBooksDataPagingSource(apiService, apiKey, query, queryType, searchType) }
        ).flow
    }

    override fun getAllNewBookList(categoryId: Int): Flow<List<BooksModel.Response.BooksItem>> {
        return flow {
            val response = apiService.getNewBooks(apiKey, categoryId)

            emit(response.item)
        }
    }

    override fun getAllRecommendBookList(): Flow<List<BooksModel.Response.BooksItem>> {
        return flow {
            val response = apiService.getRecommendBooks(apiKey, 100)

            emit(response.item)
        }
    }
}