package com.example.booksapp.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.*
import com.example.booksapp.databinding.*

class BookDetailAdapter : ListAdapter<BooksModel.Response.BooksItem, BookDetailViewHolder>(
    object: DiffUtil.ItemCallback<BooksModel.Response.BooksItem>() {
        override fun areItemsTheSame(oldItem: BooksModel.Response.BooksItem, newItem: BooksModel.Response.BooksItem): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: BooksModel.Response.BooksItem, newItem: BooksModel.Response.BooksItem): Boolean {
            return oldItem == newItem
        }
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookDetailViewHolder{
        return BookDetailViewHolder(
            ItemBookDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookDetailViewHolder, position: Int) {

        val item = getItem(position)

        holder.onBind(item)
    }
}