package com.example.booksapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booksapp.api.model.BooksModel

@Database(entities = [BooksModel.Response.BooksItem::class], version = 1)
abstract class BookMarkDatabase  : RoomDatabase(){
    abstract fun bookDao() : BookDao

    companion object {
        fun newInstance(context : Context) : BookMarkDatabase {
            return Room.databaseBuilder(context, BookMarkDatabase::class.java, "book_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}