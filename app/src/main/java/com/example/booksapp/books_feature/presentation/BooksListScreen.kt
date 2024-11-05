package com.example.booksapp.books_feature.presentation


import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontWeight
import com.example.booksapp.books_feature.presentation.component.BookContent
import com.example.booksapp.books_feature.presentation.state.BookListState
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.data.constants.BookFilterType
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize20Style

@Composable
fun BookListScreen(
    viewModel: BooksViewModel,
    type: BookFilterType,
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit
) {
    val uiState = viewModel.uiState

    LaunchedEffect(type) {
        viewModel.getBookListByType(type)
    }


    val titleText: String
    val content: List<Book>

    when(type) {
        BookFilterType.LOCAL -> {
            titleText = "국내 도서 리스트"
            content = uiState.localBooks ?: listOf()
        }
        BookFilterType.GLOBAL -> {
            titleText = "외국 도서 리스트"
            content = uiState.foreignBooks ?: listOf()
        }
        else -> {
            titleText = "국내 추천 도서 리스트"
            content = uiState.recommendedBooks ?: listOf()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = AppColors.white,
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                title = {
                    Text(
                        text = titleText,
                        style = fontSize20Style.copy(fontWeight = FontWeight.Bold)
                    )
                },

            )
        },
       content = { paddingValues ->
           BookContent(
               paddingValues = paddingValues,
               content = content,
               type = type.name,
               onItemClick = onItemClick
           )
       }
    )
}