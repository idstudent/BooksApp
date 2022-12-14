package com.example.booksapp.data.db

import androidx.room.*
import com.example.booksapp.data.api.model.BooksModel
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book : BooksModel.Response.BooksItem)

    @Delete
    suspend fun deleteBook(book : BooksModel.Response.BooksItem)

    @Query("SELECT * FROM book")
    fun selectBooks() : Flow<List<BooksModel.Response.BooksItem>>
}