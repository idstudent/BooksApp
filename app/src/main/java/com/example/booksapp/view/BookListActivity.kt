package com.example.booksapp.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.constants.BookFilterType
import com.example.booksapp.databinding.ActivityBookListBinding
import com.example.booksapp.view.adapter.BookDetailAdapter
import com.example.booksapp.view.util.setOnSingleClickListener
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class BookListActivity : BaseActivity<ActivityBookListBinding>() {
    private val booksViewModel: BooksViewModel by inject()
    private val bookDetailAdapter = BookDetailAdapter()

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
                    booksViewModel.getBestSellerBookLIst(categoryId.toInt()).collect {
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