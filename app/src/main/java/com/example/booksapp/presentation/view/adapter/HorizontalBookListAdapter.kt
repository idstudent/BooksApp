package com.example.booksapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.booksapp.core.data.remote.model.Book
import com.example.booksapp.databinding.ItemBookBinding
import com.example.booksapp.presentation.view.adapter.ViewHolder.BookViewHolder

class HorizontalBookListAdapter : ListAdapter<Book, BookViewHolder>(
    object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun getItemCount(): Int {
        return if(super.getItemCount() > 6) 6
        else super.getItemCount()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }
}