package com.example.booksapp.db

import androidx.room.*
import com.example.booksapp.api.model.Books
import com.example.booksapp.api.model.BooksModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book : BooksModel.Response.BooksItem)

    @Delete
    suspend fun deleteBook(book : BooksModel.Response.BooksItem)

    @Query("SELECT * FROM book")
    suspend fun selectBooks() : List<BooksModel.Response.BooksItem>
}