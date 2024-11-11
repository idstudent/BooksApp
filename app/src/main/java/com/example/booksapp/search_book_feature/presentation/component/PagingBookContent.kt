package com.example.booksapp.search_book_feature.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.booksapp.books_feature.presentation.component.BookItem
import com.example.booksapp.core.domain.model.Book

@Composable
fun PagingBookContent(
    pagingBookList: LazyPagingItems<Book>,
    type: String,
    onItemClick: (String, String) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        items(pagingBookList.itemCount) { index ->
            val book = pagingBookList[index]
            book?.let {
                BookItem(
                    book = it,
                    type = type,
                    onItemClick = onItemClick
                )
            }
        }
    }
}