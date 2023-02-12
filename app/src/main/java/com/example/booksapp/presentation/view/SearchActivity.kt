package com.example.booksapp.presentation.view

import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.databinding.ActivitySearchBinding
import com.example.booksapp.presentation.view.adapter.BookSearchPagingAdapter
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
import com.example.booksapp.presentation.viewmodel.SearchBookViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>() {
    private val searchBookViewModel: SearchBookViewModel by viewModels()
    private val bookSearchPagingAdapter = BookSearchPagingAdapter()

    override val layoutId: Int
        get() = R.layout.activity_search

    override fun initView() {
        super.initView()

        binding.run {
            vm = searchBookViewModel
            lifecycleOwner = this@SearchActivity

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

            btnSearch.setOnSingleClickListener {
                if (etSearch.text.toString().isEmpty()) {
                    Toast.makeText(this@SearchActivity, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                } else {
                    val inputText = etSearch.text.toString()

                    if (foreignCheck) {
                        searchBook(inputText,"foreign")
                    } else {
                        searchBook(inputText, "book")
                    }
                }
            }

            tvFilter.setOnSingleClickListener {
                if (!isFinishing) {
                    SearchFilterBottomFragment().show(
                        supportFragmentManager,
                        SearchFilterBottomFragment::class.java.name
                    )
                }
            }
        }
    }

    private fun searchBook(inputText: String, type: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchBookViewModel.getSearchBooks(inputText, searchBookViewModel.searchFilter.value, type).collect {
                    bookSearchPagingAdapter.submitData(it)
                }
            }
        }
    }
}