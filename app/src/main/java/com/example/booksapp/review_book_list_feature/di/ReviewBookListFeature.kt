package com.example.booksapp.review_book_list_feature.di

import com.example.booksapp.core.domain.BookInfoRepository
import com.example.booksapp.review_book_list_feature.data.GetReviewBookListUseCaseImpl
import com.example.booksapp.review_book_list_feature.domain.GetReviewBookListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReviewBookListFeature {
    @Provides
    @Singleton
    fun provideGetReviewBookListUseCase(bookInfoRepository: BookInfoRepository): GetReviewBookListUseCase {
        return GetReviewBookListUseCaseImpl(bookInfoRepository)
    }
}