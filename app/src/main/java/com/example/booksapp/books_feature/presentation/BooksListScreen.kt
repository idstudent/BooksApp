package com.example.booksapp.books_feature.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.booksapp.books_feature.presentation.component.BookContent
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
        BookFilterType.LOCAL, BookFilterType.RECOMMEND -> {
            titleText = "국내 도서 리스트"
            content = uiState.localBooks ?: listOf()
        }
        BookFilterType.GLOBAL -> {
            titleText = "외국 도서 리스트"
            content = uiState.foreignBooks ?: listOf()
        }
        BookFilterType.BEST_LOCAL ->{
            titleText = "국내 베스트셀러 리스트"
            content = uiState.recommendedBooks ?: listOf()
        }
        BookFilterType.BEST_GLOBAL ->{
            titleText = "외국 베스트셀러 리스트"
            content = uiState.recommendedBooks ?: listOf()
        }
        BookFilterType.NEW -> {
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
           if(content.isNotEmpty()) {
               BookContent(
                   paddingValues = paddingValues,
                   content = content,
                   type = type.name,
                   onItemClick = onItemClick
               )
           }else {
               Box(
                   modifier = Modifier.fillMaxSize(),
                   contentAlignment = Alignment.Center
               ) {
                   Text(
                       text = "베스트 셀러가 없어요",
                       style = fontSize20Style,
                   )
               }

           }
       }
    )
}