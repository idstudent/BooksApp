package com.example.booksapp.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.booksapp.R
import com.example.booksapp.data.api.model.Books
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.databinding.ActivityBookDetailBinding
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
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
//    override val layoutId: Int
//        get() = R.layout.activity_book_detail
//
//    override fun initViewModel() {
//        super.initViewModel()
//

//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                bookDetailViewModel.getBookDetailInfo(isbn, "isbn", searchType).collect {
//                    if (it.isNotEmpty()) {
//                        binding.item = it[0]
//                    }
//                }
//                bookDetailViewModel.selectBook().collect {
//                    run loop@{
//                        it.mapIndexed { _, booksItem ->
//                            if (booksItem.itemId == binding.item?.itemId) {
//                                setStatusBtn(true)
//
//                                return@loop
//                            } else {
//                                setStatusBtn(false)
//                            }
//                        }
//                    }
//                }
//            }
//        }
//
//        binding.run {
//            ivBookMarkOff.setOnSingleClickListener {
//                lifecycleScope.launch {
//                    repeatOnLifecycle(Lifecycle.State.STARTED) {
//                        item?.let {
//                            bookDetailViewModel.insertBook(it)
//                            setStatusBtn(true)
//                        }
//
//                    }
//                }
//            }
//
//            ivBookMarkOn.setOnSingleClickListener {
//                lifecycleScope.launch {
//                    repeatOnLifecycle(Lifecycle.State.STARTED) {
//                        item?.let {
//                            bookDetailViewModel.deleteBook(it)
//                            setStatusBtn(false)
//                        }
//                    }
//                }
//            }
//
//            tvWriteReview.setOnSingleClickListener {
//                val intent = Intent(this@BookDetailActivity, WriteReportActivity::class.java)
//                intent.putExtra("book",binding.item)
//                startActivity(intent)
//            }
//        }
//    }
//
//    private fun setStatusBtn(on: Boolean) {
//        binding.run {
//            if (on) {
//                ivBookMarkOff.visibility = View.INVISIBLE
//                ivBookMarkOn.visibility = View.VISIBLE
//            } else {
//                ivBookMarkOff.visibility = View.VISIBLE
//                ivBookMarkOn.visibility = View.INVISIBLE
//            }
//        }
//    }
}

@Composable
fun BookDetailScreenView(
    bookDetailViewModel: BookDetailViewModel,
    isbn: String,
    searchType: String
) {
    val scrollState = rememberScrollState()

    val books = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }

    LaunchedEffect(key1 = false) {
        bookDetailViewModel.getBookDetailInfo(isbn, "isbn", searchType).collect {
            Log.e("ljy", "$it")
            books.addAll(it)
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
                    onClick = { },
                    border = BorderStroke(0.5.dp, Color.Black)

                ) {
                    Text(
                        text = "리뷰쓰기",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.Black,
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_bookmark_border_24),
                    contentDescription = "bookmark",
                    modifier = Modifier
                        .padding(top = 8.dp, end = 20.dp)
                        .clickable {}
                )
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
                    text = books[0].priceSales.toString(),
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )

                Text(
                    text = books[0].priceStandard.toString(),
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 4.dp, top = 6.dp)
                )

                Text(
                    text = "${books[0].discountRate}%",
                    color = Color.Black,
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(start = 12.dp, top = 6.dp)
                )

                Text(
                    text = "품절",
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
            Text(
                text = books[0].pubDate ?: "",
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
                    .padding(top = 8.dp, start = 20.dp, end = 20.dp)
            )
        }

    }
}