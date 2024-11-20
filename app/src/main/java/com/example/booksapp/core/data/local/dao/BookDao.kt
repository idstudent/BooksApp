package com.example.booksapp.core.data.local.dao

import androidx.room.*
import com.example.booksapp.core.domain.model.Book
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLike(book : Book)

    @Delete
    suspend fun deleteLike(book : Book)

    @Query("SELECT * FROM book")
    fun selectBookList() : Flow<List<Book>>

    @Query("SELECT * FROM book WHERE id = :id")
    suspend fun isLike(id: Int): Book?
}