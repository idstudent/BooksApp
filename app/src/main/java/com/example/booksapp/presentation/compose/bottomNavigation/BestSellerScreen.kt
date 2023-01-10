package com.example.booksapp.presentation.compose.bottomNavigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
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
    val localBooks = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }
    val globalBooks = remember { ArrayList<BooksModel.Response.BooksItem>() }
    var count by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = false) {
        bestSellerViewModel.getBestSellerBookLIst(100).collect {
            localBooks.addAll(it)
        }
    }

    HorizontalPager(
        count = 5,
        contentPadding = PaddingValues(horizontal = 32.dp),
        modifier = Modifier.fillMaxSize()
    ) { page ->
        Card(
            Modifier
                .graphicsLayer {
                    // Calculate the absolute offset for the current page from the
                    // scroll position. We use the absolute value which allows us to mirror
                    // any effects for both directions
                    val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                    // We animate the scaleX + scaleY, between 85% and 100%
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }

                    // We animate the alpha, between 50% and 100%
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
        ) {
            if (localBooks.size > 0) {
                GlideImage(
                    imageModel = { localBooks[page].coverLargeUrl },
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.FillBounds
                    ),
                    modifier = Modifier
                        .width(600.dp)
                        .height(400.dp)
                )
            }
        }
    }
}