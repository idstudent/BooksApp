package com.example.booksapp.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.databinding.ItemBookDetailBinding

class BookDetailViewHolder(
    private val binding : ItemBookDetailBinding
) : RecyclerView.ViewHolder(binding.root){

    fun onBind(item : BooksModel.Response.BooksItem) {
        binding.item = item
    }
}