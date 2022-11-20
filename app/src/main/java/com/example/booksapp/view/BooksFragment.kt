package com.example.booksapp.view

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.example.booksapp.R
import com.example.booksapp.api.ApiService
import com.example.booksapp.api.model.Books
import com.example.booksapp.databinding.FragmentBooksBinding
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class BooksFragment : BaseFragment<FragmentBooksBinding>() {
    private val booksViewModel : BooksViewModel by inject()

    override val layoutId: Int
        get() = R.layout.fragment_books

    override fun initView() {
        super.initView()

        val test = mutableListOf<Books>()

        lifecycleScope.launch {
                booksViewModel.getNewBookList(100).collect{
                    test.addAll(it)
                }

                booksViewModel.getNewBookList(200).collect {
                    test.addAll(it)
                }
        }
    }
}