package com.example.booksapp.view.adapter

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.Book
import com.example.booksapp.databinding.ItemBookBinding
import com.example.booksapp.view.BookDetailActivity
import com.example.booksapp.view.util.setOnSingleClickListener

class BookViewHolder(
    private val binding : ItemBookBinding
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
    fun onBind(item : Book) {
        binding.item = item
    }
}