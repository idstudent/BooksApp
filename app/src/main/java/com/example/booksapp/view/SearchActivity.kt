package com.example.booksapp.view

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivitySearchBinding
import com.example.booksapp.view.adapter.BookSearchPagingAdapter
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val booksViewModel: BooksViewModel by inject()
    private val bookSearchPagingAdapter = BookSearchPagingAdapter()

    override val layoutId: Int
        get() = R.layout.activity_search

    override fun initView() {
        super.initView()

        binding.run {
            rvSearchBooks.layoutManager = GridLayoutManager(this@SearchActivity, 2)
            rvSearchBooks.adapter = bookSearchPagingAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()

    }

    override fun initListener() {
        super.initListener()

        binding.run {
            etSearch.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    lifecycleScope.launch {
                        repeatOnLifecycle(Lifecycle.State.STARTED) {
                            val inputText = v.text.toString()

                            booksViewModel.getSearchBooks(inputText, "title", "book").collect {
                                bookSearchPagingAdapter.submitData(it)
                            }
                        }
                    }
                }
                false
            }
        }
    }
}