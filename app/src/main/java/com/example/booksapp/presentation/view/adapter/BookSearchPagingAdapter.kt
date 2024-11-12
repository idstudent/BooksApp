package com.example.booksapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.booksapp.core.data.remote.model.BooksModel
import com.example.booksapp.databinding.ItemBookDetailBinding
import com.example.booksapp.presentation.view.adapter.ViewHolder.BookDetailViewHolder

class BookSearchPagingAdapter : PagingDataAdapter<BooksModel.Response.BooksItem, BookDetailViewHolder>(
    object : DiffUtil.ItemCallback<BooksModel.Response.BooksItem>() {
        override fun areItemsTheSame(oldItem: BooksModel.Response.BooksItem, newItem: BooksModel.Response.BooksItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: BooksModel.Response.BooksItem, newItem: BooksModel.Response.BooksItem): Boolean {
            return oldItem == newItem
        }

    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookDetailViewHolder {
        return BookDetailViewHolder(
            ItemBookDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookDetailViewHolder, position: Int) {
        val item = getItem(position)

        item?.let { holder.onBind(it) }
    }

}