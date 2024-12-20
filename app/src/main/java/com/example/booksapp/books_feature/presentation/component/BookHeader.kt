package com.example.booksapp.books_feature.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.booksapp.R
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize20Style

@Composable
fun BookHeader(
    moveSearchBook: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .aspectRatio(360f / 120f)
            .background(
                color = AppColors.color4caf50,
                shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
            )
    ) {
         Row(
             modifier = Modifier
                 .fillMaxWidth()
                 .padding(20.dp),
             horizontalArrangement = Arrangement.SpaceBetween,
             verticalAlignment = Alignment.CenterVertically
         ) {
             Text(
                 text = "이번에 나온\n신간 도서들을 둘러보세요!",
                 style = fontSize20Style.copy(color = AppColors.white)
             )

             Image(
                 painter = painterResource(id = R.drawable.ic_baseline_search_24),
                 contentDescription = null,
                 colorFilter = ColorFilter.tint(AppColors.white),
                 modifier = Modifier
                     .size(24.dp)
                     .clickable { moveSearchBook() }
             )
         }
    }
}

@Preview
@Composable
fun BookHeaderPreview() {
    BookHeader(moveSearchBook = {})
}