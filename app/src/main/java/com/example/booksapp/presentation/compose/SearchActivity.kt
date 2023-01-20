package com.example.booksapp.presentation.compose

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.booksapp.R
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.presentation.compose.BookDetailActivity
import com.example.booksapp.presentation.viewmodel.SearchBookViewModel
import com.holix.android.bottomsheetdialog.compose.BottomSheetDialog
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    private val searchBookViewModel: SearchBookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SearchScreenView(searchBookViewModel)
        }
    }
}

@Composable
fun SearchScreenView(
    searchBookViewModel: SearchBookViewModel,
    context: Context = LocalContext.current
) {
    val checkedState = remember { mutableStateOf(false) }
    val searchString = remember { mutableStateOf("") }
    val searchFilter by searchBookViewModel.searchFilter.observeAsState("title")

    val foreignCheck = remember { mutableStateOf("book") }
    val trySearchText by searchBookViewModel.searchString.observeAsState("")

    var bookItems: LazyPagingItems<BooksModel.Response.BooksItem>? = remember { null }
    var bottomSheetShow by remember {
        mutableStateOf(false)
    }

    if (trySearchText != "" && searchFilter != "") {
        bookItems = searchBookViewModel.getSearchBooks(
            searchString.value,
            searchFilter,
            foreignCheck.value
        ).collectAsLazyPagingItems()
    }

    if (bottomSheetShow) {
        BottomSheetDialog(
            onDismissRequest = {
                bottomSheetShow = false
            }
        ) {
            Surface(
                color = Color.White,
                shape = RoundedCornerShape(12f, 12f)
            ) {

                Column {
                    Text(
                        text = "제목",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 12.dp)
                            .clickable {
                                searchBookViewModel.setFilter("title")
                                bottomSheetShow = false
                            }
                    )
                    Text(
                        text = "저자",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 12.dp)
                            .clickable {
                                searchBookViewModel.setFilter("author")
                                bottomSheetShow = false
                            }
                    )
                    Text(
                        text = "출판사",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp, start = 12.dp)
                            .clickable {
                                searchBookViewModel.setFilter("publisher")
                                bottomSheetShow = false
                            }
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth()
        ) {
            val (btnBack, etSearch, btnSearch, checkbox, foreignBookText, filter) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.ic_btn_title_back),
                contentDescription = "search",
                colorFilter = ColorFilter.tint(Color.Black),
                modifier = Modifier
                    .constrainAs(btnBack) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 20.dp, start = 14.dp)
                    .clickable {
                        (context as Activity).finish()
                    }
            )

            TextField(
                value = searchString.value,
                onValueChange = { searchString.value = it },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                placeholder = { Text("검색어를 입력하세요.") },
                maxLines = 1,
                modifier = Modifier
                    .constrainAs(etSearch) {
                        top.linkTo(parent.top)
                        start.linkTo(btnBack.end)
                        end.linkTo(btnSearch.start)
                        width = Dimension.fillToConstraints
                    }
                    .padding(top = 6.dp, start = 12.dp, end = 12.dp)
            )
            OutlinedButton(
                onClick = {
                    if (searchString.value != "") {
                        searchBookViewModel.setClickSearch(searchString.value)
                    } else {
                        Toast.makeText(context, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                    }
                },
                border = BorderStroke(0.5.dp, Color.Black),
                modifier = Modifier
                    .constrainAs(btnSearch) {
                        top.linkTo(etSearch.top)
                        bottom.linkTo(etSearch.bottom)
                        start.linkTo(etSearch.end)
                        end.linkTo(parent.end)
                    }
                    .padding(end = 12.dp)

            ) {
                Text(
                    text = "검색",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            Checkbox(
                checked = checkedState.value,
                onCheckedChange = {
                    checkedState.value = it

                    if (checkedState.value) {
                        foreignCheck.value = "foreign"
                    } else {
                        foreignCheck.value = "book"
                    }
                },
                modifier = Modifier
                    .constrainAs(checkbox) {
                        top.linkTo(btnBack.bottom)
                        start.linkTo(parent.start)
                    }
                    .padding(top = 20.dp, start = 8.dp)
            )

            Text(
                text = "외국도서 보기",
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(foreignBookText) {
                        top.linkTo(etSearch.bottom)
                        start.linkTo(checkbox.end)
                    }
                    .padding(top = 12.dp, start = 8.dp)
            )

            Text(
                text = "제목",
                fontSize = 14.sp,
                color = Color.Black,
                modifier = Modifier
                    .constrainAs(filter) {
                        top.linkTo(foreignBookText.top)
                        bottom.linkTo(foreignBookText.bottom)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 4.dp, end = 16.dp)
                    .clickable {
                        bottomSheetShow = true
                    }
            )
        }

        if (bookItems == null) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "검색 결과가 없어요!",
                    fontSize = 20.sp
                )
            }

        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                state = rememberLazyGridState(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
            ) {
                items(bookItems.itemCount) { index ->
                    Column(
                        modifier = Modifier
                            .padding(start = 2.dp, end = 2.dp, bottom = 12.dp)
                            .clickable {
                                val intent = Intent(context, BookDetailActivity::class.java)
                                intent.putExtra("isbn", bookItems[index]?.isbn)
                                if(bookItems[index]?.categoryId == "200") {
                                    intent.putExtra("searchType", "foreign")
                                }
                                context.startActivity(intent)
                            }
                    ) {
                        GlideImage(
                            imageModel = {
                                bookItems[index]?.coverLargeUrl
                            },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.FillBounds
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )

                        Text(
                            text = bookItems[index]?.title ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp, top = 8.dp)
                        )


                        Text(
                            text = bookItems[index]?.publisher ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp, top = 4.dp)
                        )


                        Text(
                            text = bookItems[index]?.author ?: "",
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
