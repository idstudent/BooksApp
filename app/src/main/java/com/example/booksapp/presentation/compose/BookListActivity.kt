package com.example.booksapp.presentation.compose

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.R
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.data.constants.BookFilterType
import com.example.booksapp.presentation.viewmodel.AllBookListViewModel
import com.example.booksapp.presentation.viewmodel.BestSellerViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookListActivity : ComponentActivity() {
    private val allBookListViewModel: AllBookListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val type = intent.getStringExtra("type") ?: throw RuntimeException()
        val categoryId = intent.getStringExtra("categoryId") ?: throw RuntimeException()

        setContent {
            BookListScreenView(allBookListViewModel, type, categoryId)
        }
    }
}


@Composable
fun BookListScreenView(
    allBookListViewModel: AllBookListViewModel,
    type: String,
    categoryId: String,
    context: Context = LocalContext.current
) {
    var title by rememberSaveable { mutableStateOf("국내도서 리스트") }
    val books = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }

    when (type) {
        BookFilterType.NEW.name -> {
            if(categoryId == "100") {
                title = "국내도서 리스트"
            }else {
                title = "외국도서 리스트"
            }

            LaunchedEffect(key1 = false) {
                allBookListViewModel.getAllNewBookList(categoryId.toInt()).collect {
                    books.clear()
                    books.addAll(it)
                }
            }
        }
        BookFilterType.RECOMMEND.name -> {
            title = "국내추천 리스트"
            LaunchedEffect(key1 = false) {
                allBookListViewModel.getAllRecommendBookList().collect {
                    books.clear()
                    books.addAll(it)
                }
            }
        }
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_btn_title_back),
                contentDescription = "search",
                modifier = Modifier
                    .padding(top = 20.dp, start = 14.dp)
                    .clickable {
                        (context as Activity).finish()
                    }
            )

            Text(
                text = title,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 16.dp, start = 20.dp)
            )
        }

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