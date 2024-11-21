package com.example.booksapp.best_seller_book_feature.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.booksapp.core.uitl.BookFilterType
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize14Style
import com.example.booksapp.ui.theme.fontSize16Style
import com.example.booksapp.ui.theme.fontSize20Style
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun BestSellerBookScreen(
    viewModel: BestSellerBookViewModel,
    moveList: (BookFilterType) -> Unit,
    onItemClick: (String, String) -> Unit
) {
    var isLocalBookList by remember { mutableStateOf(true) }
    val uiState = viewModel.uiState
    val scope = rememberCoroutineScope()

    val currentBooks = if (isLocalBookList) uiState.localBooks else uiState.foreignBooks

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        currentBooks?.size ?: 0
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = AppColors.white)
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            uiState.error.isNotEmpty() -> {
                Text(
                    text = uiState.error,
                    modifier = Modifier.align(Alignment.Center),
                    color = AppColors.black
                )
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = AppColors.white)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, start = 16.dp, end = 16.dp)
                    ) {
                        Text(
                            text = if (isLocalBookList) "국내 베스트셀러" else "외국 베스트셀러",
                            style = fontSize20Style.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.align(Alignment.Center)
                        )

                        Text(
                            text = "더보기 >",
                            style = fontSize14Style,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .clickable {
                                    moveList(
                                        if(isLocalBookList) {
                                            BookFilterType.BEST_LOCAL
                                        } else {
                                            BookFilterType.BEST_GLOBAL
                                        }
                                    )
                                }
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = AppColors.white)
                    ) {
                        when {
                            uiState.error.isNotEmpty() -> {
                                Text(
                                    text = uiState.error,
                                    modifier = Modifier.align(Alignment.Center),
                                    color = AppColors.black
                                )
                            }

                            else -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color = AppColors.white)
                                ) {

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(1f)
                                    ) {
                                        currentBooks?.let { books ->
                                            if (books.isNotEmpty()) {
                                                HorizontalPager(
                                                    state = pagerState,
                                                    contentPadding = PaddingValues(horizontal = 70.dp),
                                                    modifier = Modifier.fillMaxSize()
                                                ) { page ->
                                                    val book = books[page]

                                                    Card(
                                                        modifier = Modifier
                                                            .graphicsLayer {
                                                                val pageOffset =
                                                                    calculateCurrentOffsetForPage(
                                                                        page,
                                                                        pagerState
                                                                    ).absoluteValue

                                                                lerp(
                                                                    start = 0.8f,
                                                                    stop = 1f,
                                                                    fraction = 1f - pageOffset.coerceIn(
                                                                        0f,
                                                                        1f
                                                                    )
                                                                ).also { scale ->
                                                                    scaleX = scale
                                                                    scaleY = scale
                                                                }
                                                            }
                                                            .fillMaxWidth()
                                                            .aspectRatio(0.5f),
                                                        elevation = 0.dp,
                                                        onClick = {
                                                            onItemClick(
                                                                book.isbn,
                                                                if(isLocalBookList){
                                                                    "book"
                                                                }else {
                                                                    "foreign"
                                                                }
                                                            )
                                                        }
                                                    ) {
                                                        Column(
                                                            modifier = Modifier.fillMaxSize()
                                                        ) {
                                                            AsyncImage(
                                                                model = book.coverLargeUrl,
                                                                contentDescription = book.title,
                                                                contentScale = ContentScale.Crop,
                                                                modifier = Modifier
                                                                    .fillMaxWidth()
                                                                    .weight(1f)
                                                            )

                                                            Text(
                                                                text = book.title,
                                                                style = fontSize16Style,
                                                                maxLines = 1,
                                                                overflow = TextOverflow.Ellipsis,
                                                                textAlign = TextAlign.Center,
                                                                modifier = Modifier
                                                                    .fillMaxWidth()
                                                                    .padding(top = 8.dp)
                                                            )

                                                            Text(
                                                                text = book.author,
                                                                style = fontSize14Style,
                                                                maxLines = 1,
                                                                overflow = TextOverflow.Ellipsis,
                                                                textAlign = TextAlign.Center,
                                                                modifier = Modifier
                                                                    .fillMaxWidth()
                                                                    .padding(vertical = 4.dp)
                                                            )
                                                        }
                                                    }
                                                }
                                            } else {
                                                Text(
                                                    text = "베스트 셀러가 없어요",
                                                    style = fontSize20Style,
                                                    modifier = Modifier.align(Alignment.Center)
                                                )
                                            }
                                        }
                                    }

                                    Button(
                                        onClick = {
                                            isLocalBookList = !isLocalBookList
                                            if (isLocalBookList) {
                                               viewModel.getLocalBooks()
                                            }else {
                                                viewModel.getGlobalBooks()
                                            }
                                            scope.launch {
                                                pagerState.animateScrollToPage(0)
                                            }
                                        },
                                        modifier = Modifier
                                            .padding(bottom = 14.dp)
                                            .height(48.dp)
                                            .align(Alignment.CenterHorizontally)
                                            .border(
                                                width = 0.5.dp,
                                                color = AppColors.black,
                                                shape = RoundedCornerShape(8.dp)
                                            ),
                                        colors = ButtonDefaults.buttonColors(
                                            backgroundColor = AppColors.white,
                                            contentColor = AppColors.black,
                                        ),
                                        elevation = ButtonDefaults.elevation(
                                            defaultElevation = 0.dp,  // 기본 상태의 그림자
                                            pressedElevation = 0.dp,  // 버튼을 눌렀을 때의 그림자
                                            hoveredElevation = 0.dp,  // 호버 상태의 그림자
                                            focusedElevation = 0.dp   // 포커스 상태의 그림자
                                        ),
                                        shape = RoundedCornerShape(8.dp)
                                    ) {
                                        Text(
                                            text = if (isLocalBookList) "외국도서 베스트셀러 보기"
                                            else "국내도서 베스트셀러 보기"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
private fun calculateCurrentOffsetForPage(page: Int, pagerState: PagerState): Float {
    return (pagerState.currentPage - page) + pagerState.currentPageOffsetFraction
}

private fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}

/*
    2024.11.05 베스트셀러 api 호출시 아이템이 없어서 더미데이터 생성해서 확인함
 */
//val dummyBooks = remember {
//    listOf(
//        Book(
//            id = 213643264,
//            title = "테스트 책 1",
//            description = "책 설명 1",
//            date = "20240101",
//            priceStandard = 15000,
//            priceSales = 13500,
//            discountRate = 10,
//            saleStatus = "판매중",
//            mileage = "1000",
//            mileageRate = "5",
//            coverSmallUrl = "https://qi-b.qoo10cdn.com/partner/goods_image/9/1/6/8/213599168s.jpg",
//            coverLargeUrl = "https://qi-b.qoo10cdn.com/partner/goods_image/9/1/6/8/213599168s.jpg",
//            categoryId = "100",
//            categoryName = "국내도서",
//            publisher = "출판사1",
//            customerReviewRank = 4.5,
//            author = "작가 1",
//            isbn = "9788958626701",
//            reviewCount = 100
//        ),
//        Book(
//            id = 213643264,
//            title = "테스트 책 2",
//            description = "책 설명 2",
//            date = "20240102",
//            priceStandard = 18000,
//            priceSales = 16200,
//            discountRate = 10,
//            saleStatus = "판매중",
//            mileage = "1000",
//            mileageRate = "5",
//            coverSmallUrl = "https://qi-b.qoo10cdn.com/partner/goods_image/9/1/6/8/213599168s.jpg",
//            coverLargeUrl = "https://qi-b.qoo10cdn.com/partner/goods_image/9/1/6/8/213599168s.jpg",
//            categoryId = "100",
//            categoryName = "국내도서",
//            publisher = "출판사2",
//            customerReviewRank = 4.8,
//            author = "작가 2",
//            isbn = "9788958626701",
//            reviewCount = 150
//        ),
//        Book(
//            id = 213643264,
//            title = "테스트 책 3",
//            description = "책 설명 3",
//            date = "20240103",
//            priceStandard = 20000,
//            priceSales = 18000,
//            discountRate = 10,
//            saleStatus = "판매중",
//            mileage = "1000",
//            mileageRate = "5",
//            coverSmallUrl = "https://qi-b.qoo10cdn.com/partner/goods_image/9/1/6/8/213599168s.jpg",
//            coverLargeUrl = "https://qi-b.qoo10cdn.com/partner/goods_image/9/1/6/8/213599168s.jpg",
//            categoryId = "100",
//            categoryName = "국내도서",
//            publisher = "출판사3",
//            customerReviewRank = 4.2,
//            author = "작가 3",
//            isbn = "9788958626701",
//            reviewCount = 80
//        )
//    )
//}