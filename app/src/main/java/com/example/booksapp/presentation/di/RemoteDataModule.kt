package com.example.booksapp.presentation.di

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.data.repository.dataSourceImpl.BookRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideBookRemoteDataSource(apiService: ApiService) : BookRemoteDataSource =
        BookRemoteDataSourceImpl(apiService)
}