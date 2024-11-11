package com.example.booksapp.search_book_feature.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.booksapp.core.data.mapper.toBook
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultRemoteDataSource
import retrofit2.HttpException
import java.io.IOException

class BookPagingSource(
    private val getSearchBookResultRemoteDataSource: GetSearchBookResultRemoteDataSource,
    private val title: String,
    private val filter: String,
    private val searchType: String
): PagingSource<Int, Book>() {
    override fun getRefreshKey(state: PagingState<Int, Book>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(LIMIT) ?: anchorPage?.nextKey?.minus(LIMIT)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> {
        return try {
            val page = params.key ?: 1
            val response = getSearchBookResultRemoteDataSource.getSearchBookList(page = page, title = title, filter = filter, searchType = searchType)

            LoadResult.Page(
                data = response.toBook(),
                prevKey = if (page == 1) null else page -1,
                nextKey = if (response.item.isEmpty()) null  else page+ 1
            )
        }catch (e: IOException) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }catch (e: HttpException) {
            e.printStackTrace()
            return LoadResult.Error(e)
        }
    }

    companion object {
        private const val LIMIT = 20
    }
}