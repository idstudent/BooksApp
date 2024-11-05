package com.example.booksapp.book_detail_feature.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.booksapp.R
import com.example.booksapp.core.uitl.formatDate
import com.example.booksapp.core.uitl.numberFormat
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize14Style
import com.example.booksapp.ui.theme.fontSize16Style
import com.example.booksapp.ui.theme.fontSize20Style


@Composable
fun BookDetailScreen(
    viewModel: BookDetailViewModel,
    isbn: String,
    searchType: String
) {
    val uiState = viewModel.uiState

    LaunchedEffect(key1 = Unit) {
        viewModel.getBookDetailInfo(isbn = isbn, searchType = searchType)
    }

    val detailInfo = uiState.bookDetailInfo?.get(0)

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .background(AppColors.white)
        ) {
            AsyncImage(
                model = detailInfo?.coverLargeUrl,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 20.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "리뷰쓰기",
                        style = fontSize14Style.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier
                            .background(
                                color = AppColors.white,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(
                                width = 0.5.dp,
                                color = AppColors.black,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    )

                    IconButton(
                        onClick = { /*TODO*/ }
                    ) {
                        Icon(
                            painterResource(id = R.drawable.ic_baseline_bookmark_border_24),
                            contentDescription = null,
                            tint = AppColors.color4caf50
                        )
                    }
                }

                Text(
                    text = detailInfo?.title ?: "",
                    style = fontSize20Style.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = detailInfo?.priceSales?.numberFormat.toString(),
                        style = fontSize20Style.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.alignByBaseline()
                    )

                    Text(
                        text = detailInfo?.priceStandard?.numberFormat.toString(),
                        style = fontSize14Style.copy(color = AppColors.color565656),
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .alignByBaseline()
                    )

                    Text(
                        text = "${detailInfo?.discountRate}%",
                        style = fontSize16Style.copy(color = AppColors.colorF44336),
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .alignByBaseline()
                    )

                    if(detailInfo?.saleStatus != null && detailInfo.saleStatus != "") {
                        Text(
                            text = detailInfo.saleStatus,
                            style = fontSize14Style.copy(
                                color = AppColors.colorF44336,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .background(
                                    color = AppColors.white,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .border(
                                    width = 1.dp,
                                    color = AppColors.colorF44336,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }

                if(detailInfo?.date != null && detailInfo.date != "") {
                    Text(
                        text = formatDate(detailInfo.date),
                        style = fontSize14Style,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 20.dp),
                    verticalAlignment = Alignment.CenterVertically

                ){
                    Text(
                        text = detailInfo?.publisher ?: "",
                        style = fontSize14Style
                    )

                    Text(
                        text = detailInfo?.author ?: "",
                        style = fontSize14Style,
                        modifier =  Modifier.padding(start = 4.dp)
                    )
                }

                Text(
                    text = detailInfo?.description ?: ""
                )
            }
        }
    }
}
