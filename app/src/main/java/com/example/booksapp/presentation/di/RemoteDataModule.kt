package com.example.booksapp.presentation.di

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.repository.dataSource.BookRemoteDataSource
import com.example.booksapp.data.repository.dataSourceImpl.BookRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataModule {
    @Singleton
    @Binds
    abstract fun provideBookRemoteDataSource(remoteDataSourceImpl : BookRemoteDataSourceImpl) : BookRemoteDataSource
}