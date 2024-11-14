package com.example.booksapp.book_detail_feature.data

import com.example.booksapp.book_detail_feature.data.repository.BookLikeRepositoryImpl
import com.example.booksapp.book_detail_feature.data.repository.GetBookDetailRepositoryImpl
import com.example.booksapp.book_detail_feature.data.source.BookLikeLocalDataSourceImpl
import com.example.booksapp.book_detail_feature.data.source.GetBookDetailRemoteDataSourceImpl
import com.example.booksapp.book_detail_feature.data.usecase.AddBookListUseCaseImpl
import com.example.booksapp.book_detail_feature.data.usecase.DeleteBookLikeUseCaseImpl
import com.example.booksapp.book_detail_feature.data.usecase.GetBookDetailUseCaseImpl
import com.example.booksapp.book_detail_feature.data.usecase.IsLikeBooksUseCaseImpl
import com.example.booksapp.book_detail_feature.domain.AddBookListUseCase
import com.example.booksapp.book_detail_feature.domain.BookLikeLocalDataSource
import com.example.booksapp.book_detail_feature.domain.BookLikeRepository
import com.example.booksapp.book_detail_feature.domain.DeleteBookLikeUseCase
import com.example.booksapp.book_detail_feature.domain.GetBookDetailRemoteDataSource
import com.example.booksapp.book_detail_feature.domain.GetBookDetailRepository
import com.example.booksapp.book_detail_feature.domain.GetBookDetailUseCase
import com.example.booksapp.book_detail_feature.domain.IsLikeBooksUseCase
import com.example.booksapp.core.data.local.dao.BookDao
import com.example.booksapp.core.data.remote.ApiService
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
    fun provideIsLikeBookUseCase(bookLikeRepository: BookLikeRepository): IsLikeBooksUseCase {
        return IsLikeBooksUseCaseImpl(bookLikeRepository)
    }
}