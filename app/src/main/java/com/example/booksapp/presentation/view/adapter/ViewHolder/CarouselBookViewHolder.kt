package com.example.booksapp.presentation.view.adapter.ViewHolder

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.databinding.ItemViewpagerBookBinding
import com.example.booksapp.presentation.view.BookDetailActivity
import com.example.booksapp.presentation.view.util.setOnSingleClickListener

class CarouselBookViewHolder(
    private val binding: ItemViewpagerBookBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.run {
            root.setOnSingleClickListener {
                item?.let {
                    val intent = Intent(binding.root.context, BookDetailActivity::class.java)
                    intent.putExtra("isbn", it.isbn)
                    if(it.categoryId == "200") {
                        intent.putExtra("searchType", "foreign")
                    }
                    this.root.context.startActivity(intent)
                }
            }
        }
    }

    fun onBind(item: BooksModel.Response.BooksItem) {
        binding.item = item
    }
}