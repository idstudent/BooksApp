package com.example.booksapp.data.repository

import androidx.paging.PagingData
import com.example.booksapp.core.data.remote.model.Books
import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.data.repository.dataSource.BookLocalDataSource
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.domain.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookRemoteDataSource: BookRemoteDataSource,
    private val bookLocalDataSource: BookLocalDataSource
) : BookRepository {
    override fun getNewBookList(categoryId: Int) : Flow<List<Books>> {
        return bookRemoteDataSource.getNewBookList(categoryId)
    }

    override fun getRecommendBookList(): Flow<List<Books>> {
        return bookRemoteDataSource.getRecommendBookList()
    }

    override fun getBestSellerList(categoryId: Int): Flow<List<BooksModel.Response.BooksItem>> {
        return bookRemoteDataSource.getBestSellerList(categoryId)
    }

    override fun getBookDetailInfo(isbn: String, queryType: String, searchType: String): Flow<List<BooksModel.Response.BooksItem>> {
        return bookRemoteDataSource.getBookDetailInfo(isbn, queryType, searchType)
    }

    override fun getAllNewBookList(categoryId: Int): Flow<List<BooksModel.Response.BooksItem>> {
        return bookRemoteDataSource.getAllNewBookList(categoryId)
    }

    override fun getAllRecommendBookList(): Flow<List<BooksModel.Response.BooksItem>> {
        return bookRemoteDataSource.getAllRecommendBookList()
    }

    override fun getSearchBooks(query : String, queryType : String, searchType : String) : Flow<PagingData<BooksModel.Response.BooksItem>> {
        return bookRemoteDataSource.getSearchBooks(query, queryType, searchType)
    }

    override fun selectBook(): Flow<List<BooksModel.Response.BooksItem>> {
        return bookLocalDataSource.selectBook()
    }

    override suspend fun insertBook(book: BooksModel.Response.BooksItem) {
        return bookLocalDataSource.insertBook(book)
    }

    override suspend fun deleteBook(book: BooksModel.Response.BooksItem){
        return bookLocalDataSource.deleteBook(book)
    }

    override fun selectBookReport() : Flow<List<BooksModel.Response.BooksItem>>{
        return bookLocalDataSource.selectBookReport()
    }

    override suspend fun insertBookReport(book: BooksModel.Response.BooksItem) {
        return bookLocalDataSource.insertBookReport(book)
    }

}