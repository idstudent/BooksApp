package com.example.booksapp.view

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.booksapp.R
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.databinding.ActivityWriteReviewBinding
import com.example.booksapp.view.util.setOnSingleClickListener
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyReportActivity : BaseActivity<ActivityWriteReviewBinding>() {
    private val booksViewModel: BooksViewModel by viewModel()
    private var bookItem : BooksModel.Response.BooksItem ?= null

    override val layoutId: Int
        get() = R.layout.activity_write_review

    override fun initView() {
        super.initView()

        val intent = intent
        bookItem = intent.getSerializableExtra("bookItem") as BooksModel.Response.BooksItem

        binding.item = bookItem

        binding.etReview.setText(bookItem?.review)
    }

    override fun initListener() {
        super.initListener()

        binding.run {
            btnSave.setOnSingleClickListener {
                bookItem?.let {
                    it.review = binding.etReview.text.toString()
                }

                lifecycleScope.launch {
                    repeatOnLifecycle(Lifecycle.State.STARTED) {
                        bookItem?.let {
                            booksViewModel.insertReport(it)
                            Toast.makeText(this@MyReportActivity, "저장되었습니다.", Toast.LENGTH_SHORT).show()
                            finish()
                        }
                    }
                }
            }
        }
    }
}