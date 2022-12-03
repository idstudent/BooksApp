package com.example.booksapp.presentation.view.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.databinding.ItemBookReportBinding
import com.example.booksapp.presentation.view.MyReportActivity
import com.example.booksapp.presentation.view.util.setOnSingleClickListener

class BookReportViewHolder(
    private val binding : ItemBookReportBinding
) : RecyclerView.ViewHolder(binding.root){

    init {
        binding.run {
            root.setOnSingleClickListener {
                item?.let {
                    val intent = Intent(binding.root.context, MyReportActivity::class.java)
                    intent.putExtra("bookItem", it)
                    this.root.context.startActivity(intent)
                }
            }
        }
    }

    fun onBind(item : BooksModel.Response.BooksItem) {
        binding.item = item
    }
}