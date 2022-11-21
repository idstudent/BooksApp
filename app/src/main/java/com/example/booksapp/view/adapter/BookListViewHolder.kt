package com.example.booksapp.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.BookList
import com.example.booksapp.api.model.NewBooks
import com.example.booksapp.databinding.ItemBooksBinding

class BookListViewHolder(
    binding : ItemBooksBinding
) : RecyclerView.ViewHolder(binding.root){
    private val horizontalBookListAdapter = HorizontalBookListAdapter()

    init {
        binding.run {
            rvBooks.layoutManager = LinearLayoutManager(this.root.context, RecyclerView.HORIZONTAL, false)
            rvBooks.adapter = horizontalBookListAdapter
        }
    }
    fun onBind(item : BookList) {
        horizontalBookListAdapter.submitList(item.books)
    }
}