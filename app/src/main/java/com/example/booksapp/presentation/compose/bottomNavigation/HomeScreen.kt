package com.example.booksapp.presentation.compose.bottomNavigation

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.R
import com.example.booksapp.data.api.model.BookList
import com.example.booksapp.data.api.model.Books
import com.example.booksapp.data.api.model.BooksTitle
import com.example.booksapp.data.api.model.Header
import com.example.booksapp.presentation.view.BookListActivity
import com.example.booksapp.presentation.view.SearchActivity
import com.example.booksapp.presentation.viewmodel.BooksViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreen(booksViewModel: BooksViewModel, context : Context = LocalContext.current) {
    val books = remember { mutableStateListOf<Books>() }

    LaunchedEffect(key1 = false) {
        booksViewModel.getNewBookList(100).collect {
            books.addAll(it)
        }
        booksViewModel.getNewBookList(200).collect {
            books.addAll(it)
        }

        booksViewModel.getRecommendBookList().collect {
            books.addAll(it)
        }
    }
    LazyColumn(
        modifier = Modifier.padding(bottom = 80.dp)
    ) {
        items(books) { book ->
            when (book) {
                is Header -> {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp),
                        backgroundColor = colorResource(id = R.color.color_4caf50),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "이번에 나온\n신간 도서들을 둘러보세요!",
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier.padding(start = 20.dp, top = 30.dp)
                            )
                            Image(
                                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                                contentDescription = "search",
                                colorFilter = ColorFilter.tint(Color.White),
                                modifier = Modifier
                                    .padding(top = 30.dp, end = 20.dp)
                                    .clickable {
                                        val intent = Intent(context, SearchActivity::class.java)
                                        context.startActivity(intent)
                                    }
                            )
                        }
                    }
                }
                is BooksTitle -> {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(
                            text = book.bookMainTitle,
                            color = Color.Black,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(start = 12.dp, top = 30.dp, bottom = 12.dp)
                        )
                        Text(
                            text = "더보기 >",
                            color = Color.Black,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(end = 12.dp, top = 33.dp)
                                .clickable {
                                    val intent = Intent(context, BookListActivity::class.java)
                                    intent.putExtra("type", book.filterType)
                                    intent.putExtra("categoryId", book.categoryId)
                                    context.startActivity(intent)
                                }
                        )
                    }

                }

                is BookList -> {
                    LazyRow {
                        items(book.books.take(5)) { item ->
                            Column(
                                modifier = Modifier.width(140.dp)
                            ) {
                                GlideImage(
                                    imageModel = { item.coverLargeUrl },
                                    imageOptions = ImageOptions(
                                        contentScale = ContentScale.Crop
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(160.dp)
                                        .padding(start = 4.dp, end = 4.dp)
                                )
                                Text(
                                    text = item.title,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                    modifier = Modifier
                                        .padding(start = 4.dp, end = 8.dp, top = 8.dp)
                                )

                                Text(
                                    text = item.publisher,
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(start = 4.dp, end = 8.dp, top = 4.dp)
                                )

                                Text(
                                    text = item.author,
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
    }
}


@Composable
fun AddPostScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Add Post Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}


@Composable
fun NotificationScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.teal_700))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Notification Screen",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}



