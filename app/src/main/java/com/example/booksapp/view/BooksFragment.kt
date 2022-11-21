package com.example.booksapp.view

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.api.model.Books
import com.example.booksapp.databinding.FragmentBooksBinding
import com.example.booksapp.view.adapter.BooksListAdapter
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class BooksFragment : BaseFragment<FragmentBooksBinding>() {
    private val booksViewModel : BooksViewModel by inject()

    override val layoutId: Int
        get() = R.layout.fragment_books

    override fun initView() {
        super.initView()

        val adapter = BooksListAdapter()

        val test = mutableListOf<Books>()

        lifecycleScope.launch {
                booksViewModel.getNewBookList(100).collect{
                    test.addAll(it)
                }

                booksViewModel.getNewBookList(200).collect {
                    test.addAll(it)
                }

            adapter.submitList(test)
        }



        binding.run {
            rvBooks.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvBooks.adapter = adapter
        }


    }
}