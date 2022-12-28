package com.example.booksapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.example.booksapp.data.db.BookMarkDatabase
import com.example.booksapp.data.db.BookReportDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideBookMarkDatabase(app : Application) =
        Room.databaseBuilder(app, BookMarkDatabase::class.java, "book_db")
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideBookReportDatabase(app : Application) =
        Room.databaseBuilder(app, BookReportDatabase::class.java, "book_report_db")
            .fallbackToDestructiveMigration()
            .build()

}