package com.example.booksapp.presentation.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.constants.BookFilterType
import com.example.booksapp.databinding.ActivityBookListBinding
import com.example.booksapp.presentation.view.adapter.BookListAdapter
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
import com.example.booksapp.presentation.viewmodel.BestSellerViewModel
import com.example.booksapp.presentation.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookListActivity : BaseActivity<ActivityBookListBinding>() {
    private val booksViewModel: BooksViewModel by viewModel()
    private val bestSellerBooksViewModel: BestSellerViewModel by viewModel()
    private val bookDetailAdapter = BookListAdapter()

    override val layoutId: Int
        get() = R.layout.activity_book_list

    override fun initView() {
        super.initView()

        binding.run {
            rvBooks.layoutManager = GridLayoutManager(this@BookListActivity, 2)
            rvBooks.adapter = bookDetailAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()

        val intent = intent
        val type = intent.getStringExtra("type") ?: throw RuntimeException()
        val categoryId = intent.getStringExtra("categoryId") ?: throw RuntimeException()


        if(categoryId == "100") {
            binding.tvTitle.text = "국내도서 리스트"
        }else {
            binding.tvTitle.text = "외국도서 리스트"
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                if(type == BookFilterType.NEW.name) {
                    booksViewModel.getNewBookDetailList(categoryId.toInt()).collect {
                        bookDetailAdapter.submitList(it)
                    }
                }else if(type == BookFilterType.RECOMMEND.name) {
                    booksViewModel.getRecommendBookDetailList().collect {
                        bookDetailAdapter.submitList(it)
                    }
                } else{
                    bestSellerBooksViewModel.getBestSellerBookLIst(categoryId.toInt()).collect {
                        bookDetailAdapter.submitList(it)
                    }
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()

        binding.ivBack.setOnSingleClickListener {
            finish()
        }
    }
}