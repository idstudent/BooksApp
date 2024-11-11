package com.example.booksapp.search_book_feature.di

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.search_book_feature.data.repository.GetSearchBookResultRepositoryImpl
import com.example.booksapp.search_book_feature.data.source.GetSearchBookResultRemoteDataSourceImpl
import com.example.booksapp.search_book_feature.data.usecase.GetSearchBookResultUseCaseImpl
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultRemoteDataSource
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultRepository
import com.example.booksapp.search_book_feature.domain.GetSearchBookResultUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SearchBookFeatureModule {
    @Provides
    @Singleton
    fun provideGetSearchBookRemoteDataSource(service: ApiService): GetSearchBookResultRemoteDataSource {
        return GetSearchBookResultRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetSearchBookResultRepository(remoteDataSource: GetSearchBookResultRemoteDataSource): GetSearchBookResultRepository {
        return GetSearchBookResultRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetSearchBookResultUseCase(getSearchBookResultRepository: GetSearchBookResultRepository): GetSearchBookResultUseCase {
        return GetSearchBookResultUseCaseImpl(getSearchBookResultRepository)
    }
}