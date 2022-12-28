package com.example.booksapp.presentation.view

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.databinding.FragmentBookReportBinding
import com.example.booksapp.presentation.view.adapter.BookReportListAdapter
import com.example.booksapp.presentation.viewmodel.BookReportViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookReportFragment : BaseFragment<FragmentBookReportBinding>() {
    private val bookReportViewModel: BookReportViewModel by viewModels()
    private val bookReportListAdapter = BookReportListAdapter()

    override val layoutId: Int
        get() = R.layout.fragment_book_report

    override fun initView() {
        super.initView()

        binding.run {
            rvBooks.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvBooks.adapter = bookReportListAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookReportViewModel.selectBookReport().collect {
                    bookReportListAdapter.submitList(it)
                }
            }
        }
    }
}