package com.example.booksapp.books_feature.presentation.component

import android.util.Log
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
import com.example.booksapp.data.constants.BookFilterType
import com.example.booksapp.ui.theme.AppColors

@Composable
fun BookItem(
    book: Book,
    modifier: Modifier = Modifier,
    type: String,
    onItemClick: (String, String) -> Unit
) {
    val bookType = if(type == BookFilterType.LOCAL.name) {
        "book"
    }else {
        "foreign"
    }

    Log.e("ljy", "타입? $bookType ${book.categoryId}")
    Column(
        modifier = modifier
            .width(140.dp)
            .padding(horizontal = 4.dp)
            .clickable {
                onItemClick(book.isbn, bookType)
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