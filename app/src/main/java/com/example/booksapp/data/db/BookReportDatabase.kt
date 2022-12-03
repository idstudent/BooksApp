package com.example.booksapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.booksapp.api.model.BooksModel

@Database(entities = [BooksModel.Response.BooksItem::class], version = 1)
abstract class BookReportDatabase  : RoomDatabase(){
    abstract fun bookDao() : BookDao

    companion object {
        fun newInstance(context : Context) : BookReportDatabase {
            return Room.databaseBuilder(context, BookReportDatabase::class.java, "book_report_db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}