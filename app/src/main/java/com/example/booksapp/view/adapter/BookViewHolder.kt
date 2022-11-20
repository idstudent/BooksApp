package com.example.booksapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.BooksTitle
import com.example.booksapp.databinding.ItemBookBinding

class BookViewHolder(
    binding : ItemBookBinding
) : RecyclerView.ViewHolder(binding.root){
    fun onBind(item : BooksTitle) {
    }
}