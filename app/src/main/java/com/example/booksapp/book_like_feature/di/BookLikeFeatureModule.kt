package com.example.booksapp.book_like_feature.di

import com.example.booksapp.book_detail_feature.data.repository.BookLikeRepositoryImpl
import com.example.booksapp.book_detail_feature.data.source.BookLikeLocalDataSourceImpl
import com.example.booksapp.book_like_feature.data.usecase.AddBookListUseCaseImpl
import com.example.booksapp.book_like_feature.data.usecase.DeleteBookLikeUseCaseImpl
import com.example.booksapp.book_like_feature.data.usecase.GetLikeBookListUseCaseImpl
import com.example.booksapp.book_like_feature.data.usecase.IsLikeBookUseCaseImpl
import com.example.booksapp.book_like_feature.domain.AddBookListUseCase
import com.example.booksapp.book_like_feature.domain.BookLikeLocalDataSource
import com.example.booksapp.book_like_feature.domain.BookLikeRepository
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
object BookLikeFeatureModule {
    @Provides
    @Singleton
    fun provideBookLieLocalDataSource(dao: BookDao): BookLikeLocalDataSource {
        return BookLikeLocalDataSourceImpl(dao)
    }

    @Provides
    @Singleton
    fun provideBookLikeRepository(localDataSource: BookLikeLocalDataSource): BookLikeRepository {
        return BookLikeRepositoryImpl(localDataSource)
    }

    @Provides
    @Singleton
    fun provideAddBookLikeUseCase(bookLikeRepository: BookLikeRepository): AddBookListUseCase {
        return AddBookListUseCaseImpl(bookLikeRepository)
    }

    @Provides
    @Singleton
    fun provideDeleteBookLikeUseCase(bookLikeRepository: BookLikeRepository): DeleteBookLikeUseCase {
        return DeleteBookLikeUseCaseImpl(bookLikeRepository)
    }

    @Provides
    @Singleton
    fun provideIsLikeBookUseCase(bookLikeRepository: BookLikeRepository): IsLikeBookUseCase {
        return IsLikeBookUseCaseImpl(bookLikeRepository)
    }

    @Provides
    @Singleton
    fun provideGetLikeBookListUseCase(bookLikeRepository: BookLikeRepository): GetLikeBookListUseCase {
        return GetLikeBookListUseCaseImpl(bookLikeRepository)
    }
}