package com.example.booksapp.books_feature.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.ui.theme.AppColors

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(140.dp)
            .padding(horizontal = 4.dp)
            .clickable {
                onItemClick()
            }
    ) {
        AsyncImage(
            model = book.coverLargeUrl,
            contentDescription = book.title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(140f/160f),
            contentScale = ContentScale.FillBounds
        )

        Text(
            text = book.title,
            color = AppColors.black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 8.dp)
        )

        Text(
            text = book.publisher,
            color = AppColors.black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )

        Text(
            text = book.author,
            color = AppColors.black,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp)
        )
    }
}