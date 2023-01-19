package com.example.booksapp.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.R
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.presentation.view.util.numberFormat
import com.example.booksapp.presentation.view.util.won
import com.example.booksapp.presentation.viewmodel.BookDetailViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailActivity : ComponentActivity() {
    private val bookDetailViewModel: BookDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val isbn = intent.getStringExtra("isbn") ?: throw RuntimeException()
        val searchType = intent.getStringExtra("searchType") ?: "book"

        setContent {
            BookDetailScreenView(bookDetailViewModel, isbn, searchType)
        }
    }
}

@Composable
fun BookDetailScreenView(
    bookDetailViewModel: BookDetailViewModel,
    isbn: String,
    searchType: String,
    context: Context = LocalContext.current
) {
    val scrollState = rememberScrollState()

    val books = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }
    val bookMark = remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = false) {
        bookDetailViewModel.getBookDetailInfo(isbn, "isbn", searchType).collect {
            books.addAll(it)
        }
        bookDetailViewModel.selectBook().collect {
            run loop@{
                it.mapIndexed { _, booksItem ->
                    if (booksItem.itemId == books[0].itemId) {
                        bookMark.value = true
                        return@loop
                    }
                }
            }
        }
    }

    if (books.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            GlideImage(
                imageModel = {
                    books[0].coverLargeUrl
                },
                imageOptions = ImageOptions(
                    contentScale = ContentScale.FillBounds
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        val intent = Intent(context, MyReportActivity::class.java)
                        intent.putExtra("book", books[0])
                        context.startActivity(intent)
                    },
                    border = BorderStroke(0.5.dp, Color.Black)

                ) {
                    Text(
                        text = "리뷰쓰기",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black
                    )
                }
                if (bookMark.value) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_bookmark_24),
                        contentDescription = "bookmark",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.color_4caf50)),
                        modifier = Modifier
                            .padding(top = 8.dp, end = 20.dp)
                            .clickable {
                                bookMark.value = false

                                scope.launch {
                                    bookDetailViewModel.deleteBook(books[0])
                                }
                            }
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_baseline_bookmark_border_24),
                        contentDescription = "bookmark",
                        colorFilter = ColorFilter.tint(colorResource(id = R.color.color_4caf50)),
                        modifier = Modifier
                            .padding(top = 8.dp, end = 20.dp)
                            .clickable {
                                bookMark.value = true

                                scope.launch {
                                    bookDetailViewModel.insertBook(books[0])
                                }
                            }
                    )
                }

            }

            Text(
                text = books[0].title ?: "",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            )
            Row {
                Text(
                    text = books[0].priceSales?.numberFormat?.won ?: "0원",
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )

                Text(
                    text = books[0].priceStandard?.numberFormat?.won ?: "0원",
                    style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 6.dp)
                )

                Text(
                    text = "${books[0].discountRate}%",
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 6.dp, top = 6.dp)
                )

                if (books[0].saleStatus != "") {
                    Text(
                        text = books[0].saleStatus.toString(),
                        color = Color.Red,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(start = 12.dp, top = 3.dp)
                            .border(
                                width = 0.5.dp,
                                color = Color.Red,
                                shape = RoundedCornerShape(12.dp),
                            )
                            .padding(top = 3.dp, bottom = 3.dp, start = 8.dp, end = 8.dp)

                    )
                }
            }
            Text(
                text = setDateFormat(books[0].pubDate ?: ""),
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = books[0].publisher ?: "",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 20.dp, top = 4.dp)
                )

                Text(
                    text = books[0].author ?: "",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 4.dp)
                )
            }

            Text(
                text = books[0].description ?: "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
            )
        }

    }
}

fun setDateFormat(date : String) : String{
    if(date == "" || date == null) { return "not date" }
    val dateText = "${date.substring(0,4)}-${date.substring(4,6)}-${date.substring(6,8)}"
    return dateText
}
