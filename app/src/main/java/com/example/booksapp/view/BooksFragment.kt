package com.example.booksapp.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.api.model.Books
import com.example.booksapp.databinding.FragmentBooksBinding
import com.example.booksapp.view.adapter.BookListAdapter
import com.example.booksapp.viewmodel.BooksViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class BooksFragment : BaseFragment<FragmentBooksBinding>() {
    private val booksViewModel: BooksViewModel by inject()
    private val bookListAdapter = BookListAdapter()

    override val layoutId: Int
        get() = R.layout.fragment_books

    override fun initView() {
        super.initView()

        binding.run {
            rvBooks.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvBooks.adapter = bookListAdapter
        }
    }

    override fun initViewModel() {
        super.initViewModel()


        val books = mutableListOf<Books>()


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                books.clear()

                booksViewModel.getNewBookList(100).collect {
                    books.addAll(it)
                }

                booksViewModel.getNewBookList(200).collect {
                    books.addAll(it)
                }

                booksViewModel.getRecommendBookList().collect {
                    books.addAll(it)
                }

                bookListAdapter.submitList(books)
            }
        }
    }
}