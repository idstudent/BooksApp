package com.example.booksapp

import com.example.booksapp.api.ApiService
import com.example.booksapp.api.NullOnEmptyConverterFactory
import com.example.booksapp.db.BookMarkDatabase
import com.example.booksapp.db.BookReportDatabase
import com.example.booksapp.repository.BookRepository
import com.example.booksapp.viewmodel.*
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModules {
    private val viewModels = module {
        viewModel {
            BooksViewModel(
                bookRepository = get()
            )
        }
        viewModel {
            BestSellerViewModel(
                bookRepository = get()
            )
        }
        viewModel {
            AllBookListViewModel(
                bookRepository = get()
            )
        }
        viewModel {
            BookDetailViewModel(
                bookRepository = get()
            )
        }
        viewModel {
            BookSearchViewModel(
                bookRepository = get()
            )
        }
    }

    private val repositories = module {
        factory {
            BookRepository(
                service = get(),
                bookMarkDatabase = get(),
                reportDatabase = get()
            )
        }
    }
    private val etc = module {
        single {
            Retrofit.Builder()
                .client(
                    OkHttpClient.Builder()
                        .addInterceptor(HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        })
                        .build()
                )
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(
                    GsonConverterFactory.create(
                        GsonBuilder().setLenient().create()
                    )
                )
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }

        single { BookMarkDatabase.newInstance(androidContext()) }
        single { BookReportDatabase.newInstance(androidContext()) }
    }

    val modules = listOf(viewModels, repositories, etc)
}