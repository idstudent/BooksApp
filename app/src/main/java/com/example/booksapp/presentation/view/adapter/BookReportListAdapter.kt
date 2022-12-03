package com.example.booksapp.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.booksapp.data.api.model.*
import com.example.booksapp.databinding.*

class BookReportListAdapter : ListAdapter<BooksModel.Response.BooksItem, BookReportViewHolder>(
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookReportViewHolder{
        return BookReportViewHolder(
            ItemBookReportBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookReportViewHolder, position: Int) {

        val item = getItem(position)

        holder.onBind(item)
    }
}