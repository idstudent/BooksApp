package com.example.booksapp.write_review_feature.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize14Style
import com.example.booksapp.ui.theme.fontSize20Style

@Composable
fun WriteReviewScreen(
    viewModel: WriteReviewViewModel,
    book: Book,
    onBackPressed: () -> Unit
) {
    var reviewText by remember { mutableStateOf(book.review ?: "") }
    val context = LocalContext.current

    LaunchedEffect(book.review) {
        reviewText = book.review ?: ""
    }

    LaunchedEffect(viewModel.uiState.isReviewSuccess) {
        if (viewModel.uiState.isReviewSuccess) {
            Toast.makeText(context, "리뷰가 저장되었습니다", Toast.LENGTH_SHORT).show()
            onBackPressed()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AsyncImage(
            model = book.coverLargeUrl,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f / 1f),
            contentScale = ContentScale.Crop
        )

        Text(
            text = book.title,
            style = fontSize20Style.copy(fontWeight = FontWeight.Bold),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
        )

        BasicTextField(
            value = reviewText,
            onValueChange = { reviewText = it },
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 12.dp),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = AppColors.black
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            decorationBox = { innerTextField ->
                Box {
                    if (reviewText.isEmpty()) {
                        Text(
                            text = "책에 대해 느낀점을 써보세요!",
                            style = fontSize14Style.copy(color = AppColors.color565656),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )

        Text(
            text = "저장",
            style = fontSize14Style,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
                .background(
                    color = AppColors.white,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(
                    width = 0.5.dp,
                    color = AppColors.black,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 16.dp)
                .clickable {
                    viewModel.writeReview(book.copy(review = reviewText))
                }
        )
    }
}