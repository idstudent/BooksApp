package com.example.booksapp.presentation.compose.bottomNavigation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.presentation.compose.WriteReviewActivity
import com.example.booksapp.presentation.viewmodel.BookReportViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun MyReportScreen(
    bookReportViewModel: BookReportViewModel,
    context: Context = LocalContext.current
) {
    val books = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }

    LaunchedEffect(key1 = false) {
        bookReportViewModel.selectBookReport().collect {
            books.clear()
            books.addAll(it)
        }
    }

    Column {
        Text(
            text = "나의 독후감",
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
        LazyColumn(
            modifier = Modifier.padding(top = 8.dp, bottom = 80.dp)
        ) {
            items(books.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 12.dp)
                        .clickable {
                            val intent = Intent(context, WriteReviewActivity::class.java)
                            intent.putExtra("book", books[index])
                            context.startActivity(intent)
                        }
                ) {
                    GlideImage(
                        imageModel = { books[index].coverLargeUrl },
                        imageOptions = ImageOptions(
                            contentScale = ContentScale.Fit
                        ),
                        modifier = Modifier
                            .width(140.dp)
                            .height(160.dp)
                    )

                    Column {
                        Text(
                            text = books[index].title ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 8.dp, end = 8.dp
                                )
                        )

                        Text(
                            text = books[index].publisher ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, end = 8.dp)
                        )

                        Text(
                            text = books[index].author ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp, end = 8.dp)
                        )
                    }
                }
            }
        }
    }
}