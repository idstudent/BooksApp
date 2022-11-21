package com.example.booksapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.Book
import com.example.booksapp.databinding.ItemBookBinding

class BookViewHolder(
    private val binding : ItemBookBinding
) : RecyclerView.ViewHolder(binding.root){

    fun onBind(item : Book) {
        binding.item = item
    }
}