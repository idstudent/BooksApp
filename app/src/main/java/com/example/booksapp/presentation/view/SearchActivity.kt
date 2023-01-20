package com.example.booksapp.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.CheckBox
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
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.databinding.ActivitySearchBinding
import com.example.booksapp.presentation.compose.WriteReviewActivity
import com.example.booksapp.presentation.view.adapter.BookSearchPagingAdapter
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
import com.example.booksapp.presentation.viewmodel.SearchBookViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {
    private val searchBookViewModel: SearchBookViewModel by viewModels()
    private val bookSearchPagingAdapter = BookSearchPagingAdapter()


//    override val layoutId: Int
//        get() = R.layout.activity_search
//
//    override fun initView() {
//        super.initView()
//
//        binding.run {
//            rvSearchBooks.layoutManager = GridLayoutManager(this@SearchActivity, 2)
//            rvSearchBooks.adapter = bookSearchPagingAdapter
//
//            bookSearchPagingAdapter.addLoadStateListener { loadState ->
//                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && bookSearchPagingAdapter.itemCount < 1) {
//                    rvSearchBooks.isVisible = false
//                    tvEmpty.isVisible = true
//                } else {
//                    rvSearchBooks.isVisible = true
//                    tvEmpty.isVisible = false
//                }
//            }
//        }
//    }
//
//    override fun initViewModel() {
//        super.initViewModel()
//
//        searchBookViewModel.searchFilter.observe(this) {
//            searchFilter = it
//
//            binding.run {
//                when (it) {
//                    "title" -> tvFilter.text = "제목"
//                    "author" -> tvFilter.text = "저자"
//                    "publisher" -> tvFilter.text = "출판사"
//                }
//            }
//
//        }
//    }
//
//    override fun initListener() {
//        super.initListener()
//
//        var foreignCheck = false
//        binding.run {
//            cbShowForeignBook.setOnCheckedChangeListener { _, isChecked ->
//                foreignCheck = isChecked
//            }
//
//            btnSearch.setOnSingleClickListener {
//                if (etSearch.text.toString().isEmpty()) {
//                    Toast.makeText(this@SearchActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
//                } else {
//                    val inputText = etSearch.text.toString()
//
//                    if (foreignCheck) {
//                        searchBook(inputText, searchFilter, "foreign")
//                    } else {
//                        searchBook(inputText, searchFilter, "book")
//                    }
//                }
//            }
//
//            tvFilter.setOnSingleClickListener {
//                if (!isFinishing) {
//                    SearchFilterBottomFragment().show(
//                        supportFragmentManager,
//                        SearchFilterBottomFragment::class.java.name
//                    )
//                }
//            }
//        }
//    }
//
//    private fun searchBook(inputText: String, queryType: String, type: String) {
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                searchBookViewModel.getSearchBooks(inputText, queryType, type).collect {
//                    bookSearchPagingAdapter.submitData(it)
//                }
//            }
//        }
//    }

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
    val searchFilter = remember { mutableStateOf("title") }
    val foreignCheck = remember { mutableStateOf("book") }

    val btnClick = rememberSaveable { mutableStateOf(false) }

    var bookItems: LazyPagingItems<BooksModel.Response.BooksItem>? = remember { null }

    if (btnClick.value) {
        if (searchString.value == "") {
            Toast.makeText(context, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
        } else {
            bookItems = searchBookViewModel.getSearchBooks(
                searchString.value,
                searchFilter.value,
                foreignCheck.value
            ).collectAsLazyPagingItems()
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
                    btnClick.value = true
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
                    btnClick.value = false

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
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            if (bookItems == null || bookItems.itemCount == 0) {
                Log.e("ljy", "없음")
            } else {
                items(bookItems.itemCount) { index ->
                    Column(
                        modifier = Modifier.padding(start = 2.dp, end = 2.dp, bottom = 12.dp)
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
