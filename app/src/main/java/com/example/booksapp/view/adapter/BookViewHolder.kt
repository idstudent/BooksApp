package com.example.booksapp.view.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.Book
import com.example.booksapp.api.model.NewBooks
import com.example.booksapp.databinding.ItemBookBinding
import com.example.booksapp.databinding.ItemBooksBinding

class BookViewHolder(
    private val binding : ItemBookBinding
) : RecyclerView.ViewHolder(binding.root){

    fun onBind(item : Book) {
        binding.item = item
    }
}