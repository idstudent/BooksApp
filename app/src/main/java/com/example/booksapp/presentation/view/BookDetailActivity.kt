package com.example.booksapp.presentation.view

import android.content.Intent
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivityBookDetailBinding
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
import com.example.booksapp.presentation.viewmodel.BookDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookDetailActivity : BaseActivity<ActivityBookDetailBinding>() {
    private val bookDetailViewModel: BookDetailViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.activity_book_detail

    override fun initViewModel() {
        super.initViewModel()

        val intent = intent
        val isbn = intent.getStringExtra("isbn") ?: throw RuntimeException()
        val searchType = intent.getStringExtra("searchType") ?: "book"

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookDetailViewModel.getBookDetailInfo(isbn, "isbn", searchType).collect {
                    if (it.isNotEmpty()) {
                        binding.item = it[0]
                    }
                }
                bookDetailViewModel.selectBook().collect {
                    run loop@{
                        it.mapIndexed { _, booksItem ->
                            if (booksItem.itemId == binding.item?.itemId) {
                                setStatusBtn(true)

                                return@loop
                            } else {
                                setStatusBtn(false)
                            }
                        }
                    }
                }
            }
        }

        binding.run {
            ivBookMarkOff.setOnSingleClickListener {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        item?.let {
                            bookDetailViewModel.insertBook(it)
                            setStatusBtn(true)
                        }

                    }
                }
            }

            ivBookMarkOn.setOnSingleClickListener {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        item?.let {
                            bookDetailViewModel.deleteBook(it)
                            setStatusBtn(false)
                        }
                    }
                }
            }

            tvWriteReview.setOnSingleClickListener {
                val intent = Intent(this@BookDetailActivity, WriteReportActivity::class.java)
                intent.putExtra("book",binding.item)
                startActivity(intent)
            }
        }
    }

    private fun setStatusBtn(on: Boolean) {
        binding.run {
            if (on) {
                ivBookMarkOff.visibility = View.INVISIBLE
                ivBookMarkOn.visibility = View.VISIBLE
            } else {
                ivBookMarkOff.visibility = View.VISIBLE
                ivBookMarkOn.visibility = View.INVISIBLE
            }
        }
    }

}