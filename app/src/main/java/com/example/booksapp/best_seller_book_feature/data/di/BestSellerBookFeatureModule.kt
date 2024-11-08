package com.example.booksapp.best_seller_book_feature.data.di

import com.example.booksapp.best_seller_book_feature.data.repository.GetBestSellerBookListRepositoryImpl
import com.example.booksapp.best_seller_book_feature.data.source.GetBestSellerBookListRemoteDataSourceImpl
import com.example.booksapp.best_seller_book_feature.data.usecase.GetBestSellerBookListUseCaseImpl
import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListRemoteDataSource
import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListRepository
import com.example.booksapp.best_seller_book_feature.domain.GetBestSellerBookListUseCase
import com.example.booksapp.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BestSellerBookFeatureModule {
    @Provides
    @Singleton
    fun provideGetBestSellerBookListDataSource(
        service: ApiService
    ): GetBestSellerBookListRemoteDataSource{
        return GetBestSellerBookListRemoteDataSourceImpl(service)
    }

    @Provides
    @Singleton
    fun provideGetBestSellerBookListRepository(
        remoteDataSource: GetBestSellerBookListRemoteDataSource
    ): GetBestSellerBookListRepository {
        return GetBestSellerBookListRepositoryImpl(remoteDataSource)
    }

    @Provides
    @Singleton
    fun provideGetBestSellerBookListUseCase(
        getBestSellerBookListRepository: GetBestSellerBookListRepository
    ): GetBestSellerBookListUseCase {
        return GetBestSellerBookListUseCaseImpl(getBestSellerBookListRepository)
    }
}