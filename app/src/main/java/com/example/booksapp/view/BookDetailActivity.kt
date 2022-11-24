package com.example.booksapp.view

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivityBookDetailBinding
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class BookDetailActivity : BaseActivity<ActivityBookDetailBinding>() {
    private val booksViewModel: BooksViewModel by inject()

    override val layoutId: Int
        get() = R.layout.activity_book_detail

    override fun initView() {
        super.initView()

    }

    override fun initViewModel() {
        super.initViewModel()

        val intent = intent
        val isbn = intent.getStringExtra("isbn") ?: throw RuntimeException()
        val searchType = intent.getStringExtra("searchType") ?: "book"

        lifecycleScope.launch {
            booksViewModel.getBookDetailInfo(isbn, searchType).collect {
                binding.item = it[0]
            }
        }
    }
}