package com.example.booksapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.BooksTitle
import com.example.booksapp.databinding.ItemBookTitleBinding

class BookTitleViewHolder(
    binding : ItemBookTitleBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun onBind(item : BooksTitle) {
    }
}