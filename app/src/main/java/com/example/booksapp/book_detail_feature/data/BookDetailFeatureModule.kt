package com.example.booksapp.book_detail_feature.data

import com.example.booksapp.book_detail_feature.data.repository.GetBookDetailRepositoryImpl
import com.example.booksapp.book_detail_feature.data.source.GetBookDetailRemoteDataSourceImpl
import com.example.booksapp.book_detail_feature.data.usecase.GetBookDetailUseCaseImpl
import com.example.booksapp.book_detail_feature.domain.GetBookDetailRemoteDataSource
import com.example.booksapp.book_detail_feature.domain.GetBookDetailRepository
import com.example.booksapp.book_detail_feature.domain.GetBookDetailUseCase
import com.example.booksapp.books_feature.data.repository.GetBookListRepositoryImpl
import com.example.booksapp.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookDetailFeatureModule {
    @Provides
    @Singleton
    fun provideGetBookDetailRemoteDataSource(service: ApiService): GetBookDetailRemoteDataSource {
        return GetBookDetailRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetBookDetailRepository(remoteDataSource: GetBookDetailRemoteDataSource): GetBookDetailRepository {
        return GetBookDetailRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetBookDetailUseCase(getBookDetailRepository: GetBookDetailRepository): GetBookDetailUseCase {
        return GetBookDetailUseCaseImpl(getBookDetailRepository)
    }
}