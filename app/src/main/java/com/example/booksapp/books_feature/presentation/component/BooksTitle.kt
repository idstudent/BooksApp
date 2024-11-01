package com.example.booksapp.books_feature.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksapp.data.constants.BookFilterType
import com.example.booksapp.ui.theme.fontSize14Style
import com.example.booksapp.ui.theme.fontSize20Style

@Composable
fun BooksTitle(
    filterType: BookFilterType,
    onMoreClick: () -> Unit
) {
    Row(
      modifier = Modifier
          .fillMaxSize()
          .wrapContentHeight()
          .padding(horizontal = 12.dp,)
          .padding(top = 30.dp)
          .padding(bottom = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val title: String
        val categoryId : Int

        when(filterType) {
            BookFilterType.LOCAL -> {
                title = "국내 도서"
                categoryId = 100
            }
            BookFilterType.GLOBAL -> {
                title = "외국 도서"
                categoryId = 200
            }
            else -> {
                title = "국내 도서 추천"
                categoryId = 100
            }
        }

        Text(
            text = title,
            style = fontSize20Style
        )
        
        Text(
            text = "더보기 >",
            style = fontSize14Style,
            modifier = Modifier
                .clickable { onMoreClick() }
                .padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BooksTitlePreview() {
    BooksTitle(
        filterType = BookFilterType.LOCAL,
        onMoreClick = { }
    )
}