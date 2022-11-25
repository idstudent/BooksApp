package com.example.booksapp.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivityBookDetailBinding
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class BookDetailActivity : BaseActivity<ActivityBookDetailBinding>() {
    private val booksViewModel: BooksViewModel by inject()

    override val layoutId: Int
        get() = R.layout.activity_book_detail

    override fun initViewModel() {
        super.initViewModel()

        val intent = intent
        val isbn = intent.getStringExtra("isbn") ?: throw RuntimeException()
        val searchType = intent.getStringExtra("searchType") ?: "book"

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                booksViewModel.getBookDetailInfo(isbn, "isbn", searchType).collect {
                    if (it.isNotEmpty()) {
                        binding.item = it[0]
                    }
                }
            }
        }
    }
}