package com.example.booksapp.presentation.di

import com.example.booksapp.domain.repository.BookRepository
import com.example.booksapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideGetNewBookListUseCase(bookRepository: BookRepository) =
        GetNewBookListUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideGetRecommendBookListUseCase(bookRepository: BookRepository) =
        GetRecommendBookListUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideGetBestSellerBookListUseCase(bookRepository: BookRepository) =
        GetBestSellerBookListUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideGetBookDetailInfoUseCase(bookRepository: BookRepository) =
        GetBookDetailInfoUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideGetAllNewBookListUseCase(bookRepository: BookRepository) =
        GetAllNewBookListUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideGetAllRecommendBookListUseCase(bookRepository: BookRepository) =
        GetAllRecommendBookListUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideGetSearchBookListUseCase(bookRepository: BookRepository) =
        GetSearchBookListUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideLocalBookMarkUseCase(bookRepository: BookRepository) =
        LocalBookMarkUseCase(bookRepository)

    @Singleton
    @Provides
    fun provideLocalBookReportUseCase(bookRepository: BookRepository) =
        LocalBookReportUseCase(bookRepository)
}