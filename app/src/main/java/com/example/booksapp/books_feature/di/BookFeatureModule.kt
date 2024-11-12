package com.example.booksapp.books_feature.di

import com.example.booksapp.books_feature.data.repository.GetBookListRepositoryImpl
import com.example.booksapp.books_feature.data.source.GetBookRemoteDataSourceImpl
import com.example.booksapp.books_feature.data.usecase.GetBookListByTypeUseCaseImpl
import com.example.booksapp.books_feature.data.usecase.GetBookListUseCaseImpl
import com.example.booksapp.books_feature.domain.GetBookListByTypeUseCase
import com.example.booksapp.books_feature.domain.GetBookListRepository
import com.example.booksapp.books_feature.domain.GetBookListUseCase
import com.example.booksapp.books_feature.domain.GetBookRemoteDataSource
import com.example.booksapp.core.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookFeatureModule {
    @Provides
    @Singleton
    fun provideGetBookRemoteDataSource(service: ApiService): GetBookRemoteDataSource {
        return GetBookRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetBookListRepository(remoteDataSource: GetBookRemoteDataSource): GetBookListRepository {
        return GetBookListRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetBookListUseCase(getBookListRepository: GetBookListRepository): GetBookListUseCase {
        return GetBookListUseCaseImpl(getBookListRepository)
    }

    @Provides
    @Singleton
    fun provideGetBookListByTypeUseCase(getBookListRepository: GetBookListRepository): GetBookListByTypeUseCase {
        return GetBookListByTypeUseCaseImpl(getBookListRepository)
    }


}