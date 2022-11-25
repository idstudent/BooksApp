package com.example.booksapp.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentMyBooksBinding
import com.example.booksapp.view.adapter.BookListAdapter
import com.example.booksapp.view.adapter.BookSearchPagingAdapter
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MyBooksFragment : BaseFragment<FragmentMyBooksBinding>() {
    private val booksViewModel: BooksViewModel by inject()
    private val bookListAdapter = BookListAdapter()

    override val layoutId: Int
        get() = R.layout.fragment_my_books

    override fun initView() {
        super.initView()

        binding.run {
            rvBooks.layoutManager = GridLayoutManager(activity, 2)
            rvBooks.adapter = bookListAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                booksViewModel.selectBook().collect {
                    bookListAdapter.submitList(it)
                }
            }
        }
    }
}