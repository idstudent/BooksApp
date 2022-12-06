package com.example.booksapp.data.repository.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.api.model.BooksModel
import java.lang.Exception

class SearchBooksDataPagingSource(
    private val service: ApiService,
    private val apiKey : String,
    private val query: String,
    private val queryType: String,
    private val searchType: String
) : PagingSource<Int, BooksModel.Response.BooksItem>() {
    override fun getRefreshKey(state: PagingState<Int, BooksModel.Response.BooksItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BooksModel.Response.BooksItem> {
        return try {
            val page = params.key ?: 1
            val response = service.getSearchBook(apiKey, query, queryType, searchType, page = page).item

            LoadResult.Page(
                data = response,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if(response.isNotEmpty()) null else page + 1
            )
        }catch (e : Exception) {
            LoadResult.Error(e)
        }

    }

}