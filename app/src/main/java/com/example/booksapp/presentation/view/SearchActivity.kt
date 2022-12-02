package com.example.booksapp.presentation.view

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivitySearchBinding
import com.example.booksapp.presentation.view.adapter.BookSearchPagingAdapter
import com.example.booksapp.presentation.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val booksViewModel: BooksViewModel by viewModel()
    private val bookSearchPagingAdapter = BookSearchPagingAdapter()

    override val layoutId: Int
        get() = R.layout.activity_search

    override fun initView() {
        super.initView()

        binding.run {
            rvSearchBooks.layoutManager = GridLayoutManager(this@SearchActivity, 2)
            rvSearchBooks.adapter = bookSearchPagingAdapter

            bookSearchPagingAdapter.addLoadStateListener { loadState ->
                if (loadState.source.refresh is LoadState.NotLoading && loadState.append.endOfPaginationReached && bookSearchPagingAdapter.itemCount < 1) {
                    rvSearchBooks.isVisible = false
                    tvEmpty.isVisible = true
                } else {
                    rvSearchBooks.isVisible = true
                    tvEmpty.isVisible = false
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()

        var foreignCheck = false
        binding.run {
            cbShowForeignBook.setOnCheckedChangeListener { _, isChecked ->
                foreignCheck = isChecked
            }

            etSearch.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if(foreignCheck) {
                        searchBook(v, "foreign")
                    }else {
                        searchBook(v, "book")
                    }
                }
                false
            }
        }
    }

    private fun searchBook(v : TextView, type : String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                val inputText = v.text.toString()

                booksViewModel.getSearchBooks(inputText, "title", type).collect {
                    bookSearchPagingAdapter.submitData(it)
                }
            }
        }
    }
}