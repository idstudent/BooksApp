package com.example.booksapp.presentation.compose.bottomNavigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.R
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.presentation.view.BookDetailActivity
import com.example.booksapp.presentation.viewmodel.BookDetailViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MyBooksScreen(
    bookDetailViewModel: BookDetailViewModel,
    context : Context = LocalContext.current
) {
    val books = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }

    LaunchedEffect(key1 = false) {
        bookDetailViewModel.selectBook().collect {
            books.clear()
            books.addAll(it)
        }
    }

    Column {
        Text(
            text = "나의 책장",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            items(books.size) { index ->
                Box(
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Column {
                        GlideImage(
                            imageModel = {
                                books[index].coverLargeUrl
                            },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.FillBounds
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .clickable {
                                    val intent = Intent(context, BookDetailActivity::class.java)
                                    intent.putExtra("isbn", books[index].isbn)
                                    if(books[index].categoryId == "200") {
                                        intent.putExtra("searchType", "foreign")
                                    }
                                    context.startActivity(intent)
                                }
                        )

                        Text(
                            text = books[index].title ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp, top = 8.dp)
                        )


                        Text(
                            text = books[index].publisher ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp, top = 4.dp)
                        )


                        Text(
                            text = books[index].author ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp, top = 4.dp)
                        )
                    }
                }
            }
        }
    }
}