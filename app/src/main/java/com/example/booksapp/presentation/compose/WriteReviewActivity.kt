package com.example.booksapp.presentation.compose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.presentation.viewmodel.BookReportViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WriteReviewActivity : ComponentActivity() {
    private val bookReportViewModel: BookReportViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val bookInfo = intent.getSerializableExtra("book") as BooksModel.Response.BooksItem

        setContent {
            MyReportScreenView(bookReportViewModel, bookInfo)
        }
    }
}

@Composable
fun MyReportScreenView(
    bookReportViewModel : BookReportViewModel,
    bookInfo: BooksModel.Response.BooksItem,
    context: Context = LocalContext.current
) {
    val review = remember { mutableStateOf(bookInfo.review) }
    val scope = rememberCoroutineScope()

    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (bookImg, title, editReview, saveBtn) = createRefs()

        GlideImage(
            imageModel = {
                bookInfo.coverLargeUrl
            },
            imageOptions = ImageOptions(
                contentScale = ContentScale.FillBounds
            ),
            modifier = Modifier
                .constrainAs(bookImg) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(450.dp)
        )

        Text(
            text = bookInfo.title ?: "",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier
                .constrainAs(title) {
                    top.linkTo(bookImg.bottom)
                    start.linkTo(parent.start)
                }
                .padding(top = 20.dp, start = 20.dp)
        )

        TextField(
            value = review.value ?: "",
            onValueChange = { review.value = it },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier
                .constrainAs(editReview) {
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(saveBtn.top)
                    height = Dimension.fillToConstraints
                }
                .fillMaxWidth()
                .padding(top = 8.dp, start = 8.dp, end = 12.dp)
        )

        OutlinedButton(
            border = BorderStroke(0.5.dp, Color.Black),
            onClick = {
                scope.launch {
                    bookInfo.review = review.value
                    bookReportViewModel.insertReport(bookInfo)

                    Toast.makeText(context, "저장되었습니다", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .constrainAs(saveBtn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            Text(
                text = "저장",
                fontSize = 14.sp,
                color = Color.Black,
            )
        }
    }
}