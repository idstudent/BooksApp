package com.example.booksapp.view

import android.util.Log
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivityBookDetailBinding
import com.example.booksapp.view.util.setOnSingleClickListener
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
                booksViewModel.selectBook().collect {
                    run loop@{
                        it.mapIndexed { index, booksItem ->
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
                            booksViewModel.insertBook(it)
                            setStatusBtn(true)
                        }

                    }
                }
            }
            ivBookMarkOn.setOnSingleClickListener {
                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        item?.let {
                            booksViewModel.deleteBook(it)
                            setStatusBtn(false)
                        }
                    }
                }
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