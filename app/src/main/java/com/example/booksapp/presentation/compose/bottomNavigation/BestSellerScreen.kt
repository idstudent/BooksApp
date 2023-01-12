package com.example.booksapp.presentation.compose.bottomNavigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import com.bumptech.glide.request.RequestOptions
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.presentation.viewmodel.BestSellerViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BestSellerScreen(bestSellerViewModel: BestSellerViewModel) {
    val books = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }

    val requestOption = rememberSaveable { mutableStateOf(100) }

    var title by rememberSaveable { mutableStateOf("국내 베스트셀러") }
    var changeBooks by rememberSaveable { mutableStateOf("국내도서 베스트셀러 보기") }

    LaunchedEffect(key1 = requestOption.value) {
        bestSellerViewModel.getBestSellerBookLIst(requestOption.value).collect {
            books.clear()
            books.addAll(it)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                text = "",
                modifier = Modifier.weight(1f)
            )
            Text(
                text = title,
                color = Color.Black,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
            )

            Text(
                text = "더보기 >",
                color = Color.Black,
                fontSize = 14.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 4.dp, end = 12.dp),
                textAlign = TextAlign.End,
            )
        }
        HorizontalPager(
            count = 5,
            contentPadding = PaddingValues(horizontal = 32.dp),
            modifier = Modifier.padding(top = 40.dp)
        ) { page ->
            if (books.size > 0) {
                Column {
                    Card(
                        Modifier
                            .graphicsLayer {
                                val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                                lerp(
                                    start = 0.85f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                ).also { scale ->
                                    scaleX = scale
                                    scaleY = scale
                                }

                                alpha = lerp(
                                    start = 0.5f,
                                    stop = 1f,
                                    fraction = 1f - pageOffset.coerceIn(0f, 1f)
                                )
                            }
                    ) {
                        GlideImage(
                            imageModel = { books[page].coverLargeUrl },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.Fit
                            ),
                            modifier = Modifier
                                .width(600.dp)
                                .height(400.dp)
                        )
                    }

                    Text(
                        text = books[page].title ?: "",
                        color = Color.Black,
                        fontSize = 20.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp, end = 12.dp, top = 12.dp)

                    )
                }
            }
        }
        OutlinedButton(
            onClick = {
                if (changeBooks == "국내도서 베스트셀러 보기") {
                    title = "외국 베스트셀러"
                    changeBooks = "외국도서 베스트셀러 보기"
                    requestOption.value = 200
                } else {
                    title = "국내 베스트셀러"
                    changeBooks = "국내도서 베스트셀러 보기"
                    requestOption.value = 100
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp, bottom = 12.dp)
        ) {
            Text(
                text = changeBooks,
                fontSize = 20.sp,
                color = Color.Black,
            )
        }
    }
}