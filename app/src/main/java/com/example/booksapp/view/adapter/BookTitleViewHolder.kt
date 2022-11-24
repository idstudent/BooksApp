package com.example.booksapp.view.adapter

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.BooksTitle
import com.example.booksapp.constants.BookFilterType
import com.example.booksapp.databinding.ItemBookTitleBinding
import com.example.booksapp.view.BookListActivity
import com.example.booksapp.view.util.setOnSingleClickListener

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