package com.example.booksapp.books_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.booksapp.books_feature.presentation.component.BookHeader
import com.example.booksapp.books_feature.presentation.component.BooksTitle
import com.example.booksapp.data.constants.BookFilterType
import com.example.booksapp.ui.theme.white

@Composable
fun BooksScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = white)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                BookHeader()
            }
            item {
                BooksTitle(filterType = BookFilterType.LOCAL) {
                    
                }
            }
        }
    }
}

@Preview
@Composable
fun BooksScreenPreview() {
    BooksScreen()
}