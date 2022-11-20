package com.example.booksapp

import com.example.booksapp.api.ApiService
import com.example.booksapp.api.NullOnEmptyConverterFactory
import com.example.booksapp.repository.BookRepository
import com.example.booksapp.viewmodel.BooksViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.internal.platform.android.BouncyCastleSocketAdapter.Companion.factory
import okhttp3.logging.HttpLoggingInterceptor
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
    }

    private val repositories = module {
        factory {
            BookRepository(
                service = get()
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
    }

    val modules = listOf(viewModels, repositories, etc)
}