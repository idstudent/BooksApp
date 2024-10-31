package com.example.booksapp.books_feature.domain

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.booksapp.books_feature.data.mapper.toBook
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.ResultData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

interface GetBookListUseCase {
    operator fun invoke(): Flow<ResultData<Triple<List<Book>, List<Book>, List<Book>>>>

}