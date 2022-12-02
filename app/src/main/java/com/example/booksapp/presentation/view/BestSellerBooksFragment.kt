package com.example.booksapp.presentation.view

import android.content.Intent
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.example.booksapp.R
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.constants.BookFilterType
import com.example.booksapp.databinding.FragmentBestSellerBooksBinding
import com.example.booksapp.presentation.view.adapter.BooksCarouselAdapter
import com.example.booksapp.presentation.view.util.HorizontalMarginItemDecoration
import com.example.booksapp.presentation.view.util.dp
import com.example.booksapp.presentation.view.util.setOnSingleClickListener
import com.example.booksapp.presentation.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Math.abs

class BestSellerBooksFragment : BaseFragment<FragmentBestSellerBooksBinding>() {
    private val booksViewModel: BooksViewModel by viewModel()

    private val adapter = BooksCarouselAdapter()
    private val localBooks = ArrayList<BooksModel.Response.BooksItem>()
    private val globalBooks = ArrayList<BooksModel.Response.BooksItem>()

    override val layoutId: Int
        get() = R.layout.fragment_best_seller_books

    override fun initView() {
        super.initView()

        binding.run {
            vpBooks.adapter = adapter
            vpBooks.offscreenPageLimit = 1

            val pageTranslationX = 26.dp + 42.dp
            val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
                page.translationX = -pageTranslationX * position
                page.scaleY = 1 - (0.25f * abs(position))
            }
            vpBooks.setPageTransformer(pageTransformer)

            val itemDecoration = HorizontalMarginItemDecoration(42.dp)
            vpBooks.addItemDecoration(itemDecoration)

        }
    }

    override fun initViewModel() {
        super.initViewModel()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                booksViewModel.getBestSellerBookLIst(100).collect {
                    localBooks.addAll(it)

                    adapter.submitList(it)
                }
            }
        }
    }

    override fun initListener() {
        super.initListener()

        var isStatusLocal = true
        var globalCall = false

        binding.run {
            tvChange.setOnSingleClickListener {
                isStatusLocal = !isStatusLocal

                if (isStatusLocal) {
                    tvTitle.text = "국내 베스트셀러"
                    tvChange.text = "외국도서 베스트셀러 보기"
                } else {
                    tvTitle.text = "외국 베스트셀러"
                    tvChange.text = "국내도서 베스트셀러 보기"
                }

                if (isStatusLocal) {
                    adapter.submitList(localBooks)
                } else {
                    if (globalCall) {
                        adapter.submitList(globalBooks)
                    } else {
                        lifecycleScope.launch {
                            booksViewModel.getBestSellerBookLIst(200).collect {
                                globalBooks.addAll(it)
                                globalCall = true

                                adapter.submitList(it)
                            }
                        }
                    }
                }
            }

            tvMoreView.setOnSingleClickListener {
                if(isStatusLocal) {
                    moveDetailActivity("100")
                }else {
                    moveDetailActivity("200")
                }
            }
        }
    }

    private fun moveDetailActivity(categoryId: String) {
        val intent = Intent(activity, BookListActivity::class.java)
        intent.putExtra("type",  BookFilterType.BEST.name)
        intent.putExtra("categoryId", categoryId)
        startActivity(intent)
    }
}
