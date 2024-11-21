package com.example.booksapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.data.local.dao.BookDao

@Database(
    entities = [Book::class],
    version = 2,
    exportSchema = false
)
abstract class BookDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}