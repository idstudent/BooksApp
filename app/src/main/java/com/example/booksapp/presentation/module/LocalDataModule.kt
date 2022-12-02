package com.example.booksapp.presentation.module

import com.example.booksapp.db.BookMarkDatabase
import com.example.booksapp.db.BookReportDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localDataModule = module {
    single { BookMarkDatabase.newInstance(androidContext()) }
    single { BookReportDatabase.newInstance(androidContext()) }
}