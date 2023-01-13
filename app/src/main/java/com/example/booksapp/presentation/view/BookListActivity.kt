package com.example.booksapp.presentation.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.data.constants.BookFilterType
import com.example.booksapp.databinding.ActivityBookListBinding
import com.example.booksapp.presentation.view.adapter.BookListAdapter
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
import com.example.booksapp.presentation.viewmodel.AllBookListViewModel
import com.example.booksapp.presentation.viewmodel.BestSellerViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookListActivity : ComponentActivity() {
    private val allBookListViewModel: AllBookListViewModel by viewModels()
    private val bestSellerBooksViewModel: BestSellerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val type = intent.getStringExtra("type") ?: throw RuntimeException()
        val categoryId = intent.getStringExtra("categoryId") ?: throw RuntimeException()

        setContent {
            BookListScreenView(allBookListViewModel, bestSellerBooksViewModel, type, categoryId)
        }
    }


//    private val bookDetailAdapter = BookListAdapter()

//    override val layoutId: Int
//        get() = R.layout.activity_book_list
//
//    override fun initView() {
//        super.initView()
//
//        binding.run {
//            rvBooks.layoutManager = GridLayoutManager(this@BookListActivity, 2)
//            rvBooks.adapter = bookDetailAdapter
//        }
//    }
//
//    override fun initViewModel() {
//        super.initViewModel()
//
//        val intent = intent
//        val type = intent.getStringExtra("type") ?: throw RuntimeException()
//        val categoryId = intent.getStringExtra("categoryId") ?: throw RuntimeException()
//
//
//        if(categoryId == "100") {
//            binding.tvTitle.text = "국내도서 리스트"
//        }else {
//            binding.tvTitle.text = "외국도서 리스트"
//        }
//
//        lifecycleScope.launch {
//            repeatOnLifecycle(Lifecycle.State.STARTED) {
//                if(type == BookFilterType.NEW.name) {
//                    allBookListViewModel.getAllNewBookList(categoryId.toInt()).collect {
//                        bookDetailAdapter.submitList(it)
//                    }
//                }else if(type == BookFilterType.RECOMMEND.name) {
//                    allBookListViewModel.getAllRecommendBookList().collect {
//                        bookDetailAdapter.submitList(it)
//                    }
//                } else{
//                    bestSellerBooksViewModel.getBestSellerBookLIst(categoryId.toInt()).collect {
//                        bookDetailAdapter.submitList(it)
//                    }
//                }
//            }
//        }
//    }
//
//    override fun initListener() {
//        super.initListener()
//
//        binding.ivBack.setOnSingleClickListener {
//            finish()
//        }
//    }
}


@Composable
fun BookListScreenView(
    allBookListViewModel: AllBookListViewModel,
    bestSellerViewModel: BestSellerViewModel,
    type: String,
    categoryId: String,
    context: Context = LocalContext.current
) {
    val books = remember { mutableStateListOf<BooksModel.Response.BooksItem>() }

    when (type) {
        BookFilterType.NEW.name -> {
            LaunchedEffect(key1 = false) {
                allBookListViewModel.getAllNewBookList(categoryId.toInt()).collect {
                    books.clear()
                    books.addAll(it)
                }
            }
        }
        BookFilterType.RECOMMEND.name -> {
            LaunchedEffect(key1 = false) {
                allBookListViewModel.getAllRecommendBookList().collect {
                    books.clear()
                    books.addAll(it)
                }
            }
        }
        else -> {
            LaunchedEffect(key1 = false) {
                bestSellerViewModel.getBestSellerBookLIst(categoryId.toInt()).collect {
                    books.clear()
                    books.addAll(it)
                }
            }
        }
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_btn_title_back),
                contentDescription = "search",
                modifier = Modifier
                    .padding(top = 20.dp, start = 14.dp)
                    .clickable {
                        (context as Activity).finish()
                    }
            )

            Text(
                text = "국내도서 리스트",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 14.dp, start = 20.dp)
            )
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            state = rememberLazyGridState(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            items(books.size) { index ->
                Box(
                    modifier = Modifier.padding(bottom = 12.dp)
                ) {
                    Column {
                        GlideImage(
                            imageModel = {
                                books[index].coverLargeUrl
                            },
                            imageOptions = ImageOptions(
                                contentScale = ContentScale.FillBounds
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                        )

                        Text(
                            text = books[index].title ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp, top = 8.dp)
                        )


                        Text(
                            text = books[index].publisher ?: "",
                            color = Color.Black,
                            fontSize = 14.sp,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(start = 4.dp, end = 8.dp, top = 4.dp)
                        )


                        Text(
                            text = books[index].author ?: "",
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