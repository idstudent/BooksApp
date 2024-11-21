package com.example.booksapp.core.di

import com.example.booksapp.core.data.repository.BookInfoRepositoryImpl
import com.example.booksapp.core.data.source.BookInfoLocalDataSourceImpl
import com.example.booksapp.core.data.usecase.AddBookUseCaseImpl
import com.example.booksapp.book_like_feature.data.usecase.DeleteBookLikeUseCaseImpl
import com.example.booksapp.book_like_feature.data.usecase.GetLikeBookListUseCaseImpl
import com.example.booksapp.book_like_feature.data.usecase.IsLikeBookUseCaseImpl
import com.example.booksapp.core.domain.AddBookUseCase
import com.example.booksapp.core.domain.BookInfoLocalDataSource
import com.example.booksapp.core.domain.BookInfoRepository
import com.example.booksapp.book_like_feature.domain.DeleteBookLikeUseCase
import com.example.booksapp.book_like_feature.domain.GetLikeBookListUseCase
import com.example.booksapp.book_like_feature.domain.IsLikeBookUseCase
import com.example.booksapp.core.data.local.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BookInfoModule {
    @Provides
    @Singleton
    fun provideBookLieLocalDataSource(dao: BookDao): BookInfoLocalDataSource {
        return BookInfoLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideBookLikeRepository(localDataSource: BookInfoLocalDataSource): BookInfoRepository {
        return BookInfoRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideAddBookLikeUseCase(bookLikeRepository: BookInfoRepository): AddBookUseCase {
        return AddBookUseCaseImpl(bookLikeRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteBookLikeUseCase(bookLikeRepository: BookInfoRepository): DeleteBookLikeUseCase {
        return DeleteBookLikeUseCaseImpl(bookLikeRepository)
    }

    @Provides
    @Singleton
    fun provideIsLikeBookUseCase(bookLikeRepository: BookInfoRepository): IsLikeBookUseCase {
        return IsLikeBookUseCaseImpl(bookLikeRepository)
    }

    @Provides
    @Singleton
    fun provideGetLikeBookListUseCase(bookLikeRepository: BookInfoRepository): GetLikeBookListUseCase {
        return GetLikeBookListUseCaseImpl(bookLikeRepository)
    }
}