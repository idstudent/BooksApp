package com.example.booksapp.books_feature.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.booksapp.R
import com.example.booksapp.books_feature.presentation.component.BookContent
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.BookFilterType
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize20Style

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
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
        BookFilterType.LOCAL-> {
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
        BookFilterType.RECOMMEND -> {
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
                            painter = painterResource(id = R.drawable.ic_btn_title_back),
                            contentDescription = "back"
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
       content = {
           if(content.isNotEmpty()) {
               BookContent(
                   paddingValues = PaddingValues(top = 20.dp),
                   content = content,
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