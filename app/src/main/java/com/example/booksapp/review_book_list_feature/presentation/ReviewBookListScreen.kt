package com.example.booksapp.review_book_list_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.review_book_list_feature.ReviewBookListViewModel
import com.example.booksapp.review_book_list_feature.presentation.component.ReviewBookContent
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize20Style

@Composable
fun ReviewBookListScreen(
    viewModel: ReviewBookListViewModel,
    onItemClick: (Book) -> Unit
) {
    val uiState = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.white)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                text = "나의 독후감",
                style = fontSize20Style.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.align(Alignment.Center)
            )
        }

        if(uiState.books.isNotEmpty()) {
            ReviewBookContent(
                books = uiState.books,
                onItemClick = onItemClick
            )
        }
    }
}