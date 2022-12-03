package com.example.booksapp.presentation.view.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.data.api.model.Book
import com.example.booksapp.databinding.ItemBookBinding
import com.example.booksapp.presentation.view.BookDetailActivity
import com.example.booksapp.presentation.view.util.setOnSingleClickListener

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