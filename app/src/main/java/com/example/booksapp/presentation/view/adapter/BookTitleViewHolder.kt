package com.example.booksapp.presentation.view.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.data.api.model.BooksTitle
import com.example.booksapp.databinding.ItemBookTitleBinding
import com.example.booksapp.presentation.view.BookListActivity
import com.example.booksapp.presentation.view.util.setOnSingleClickListener

class BookTitleViewHolder(
    private val binding : ItemBookTitleBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.run {
            tvMoreView.setOnSingleClickListener {
                item?.let {
                    val intent = Intent(binding.root.context, BookListActivity::class.java)
                    intent.putExtra("type", it.filterType)
                    intent.putExtra("categoryId", it.categoryId)
                    root.context.startActivity(intent)
                }
            }
        }
    }

    fun onBind(item : BooksTitle) {
        binding.item = item
    }
}