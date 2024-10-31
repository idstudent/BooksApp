package com.example.booksapp.books_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksapp.books_feature.presentation.component.BookHeader
import com.example.booksapp.books_feature.presentation.component.BookItem
import com.example.booksapp.books_feature.presentation.component.BooksTitle
import com.example.booksapp.books_feature.presentation.state.BookListState
import com.example.booksapp.data.constants.BookFilterType
import com.example.booksapp.ui.theme.black
import com.example.booksapp.ui.theme.white

@Composable
fun BooksScreen(
    uiState: BookListState,
    moveList: (BookFilterType) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.error.isNotEmpty() -> {
                Text(
                    text = uiState.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = black
                )
            }

            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(bottom = 16.dp)
                ) {
                    item {
                        BookHeader()
                    }
                    // 국내도서
                    item {
                        BooksTitle(filterType = BookFilterType.LOCAL) {
                            moveList(BookFilterType.LOCAL)
                        }
                    }
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val previewBooks = uiState.localBooks?.take(5) ?: emptyList()
                            items(
                                count = previewBooks.size,
                                key = { index -> previewBooks[index].id }
                            ) { index ->
                                BookItem(book = previewBooks[index])
                            }
                        }
                    }

                    // 외국도서
                    item {
                        BooksTitle(filterType = BookFilterType.GLOBAL) {
                            moveList(BookFilterType.GLOBAL)
                        }
                    }
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val previewBooks = uiState.foreignBooks?.take(5) ?: emptyList()
                            items(
                                count = previewBooks.size,
                                key = { index -> previewBooks[index].id }
                            ) { index ->
                                BookItem(book = previewBooks[index])
                            }
                        }
                    }

                    // 국내도서 추천
                    item {
                        BooksTitle(filterType = BookFilterType.RECOMMEND) {
                            moveList(BookFilterType.RECOMMEND)
                        }
                    }
                    item {
                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            val previewBooks = uiState.recommendedBooks?.take(5) ?: emptyList()
                            items(
                                count = previewBooks.size,
                                key = { index -> previewBooks[index].id }
                            ) { index ->
                                BookItem(book = previewBooks[index])
                            }
                        }
                    }
                }
            }
        }
    }
}