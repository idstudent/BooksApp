package com.example.booksapp.view.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.databinding.ItemBookDetailBinding
import com.example.booksapp.databinding.ItemBookReportBinding
import com.example.booksapp.view.BookDetailActivity
import com.example.booksapp.view.util.setOnSingleClickListener

class BookReportViewHolder(
    private val binding : ItemBookReportBinding
) : RecyclerView.ViewHolder(binding.root){

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

    fun onBind(item : BooksModel.Response.BooksItem) {
        binding.item = item
    }
}