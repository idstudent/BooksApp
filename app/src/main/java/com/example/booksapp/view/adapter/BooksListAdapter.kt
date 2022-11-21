package com.example.booksapp.view.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.*
import com.example.booksapp.databinding.ItemBookTitleBinding
import com.example.booksapp.databinding.ItemBooksBinding
import com.example.booksapp.databinding.ItemNothingBinding

class BooksListAdapter : ListAdapter<Books, RecyclerView.ViewHolder>(
    object: DiffUtil.ItemCallback<Books>() {
        override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
            return oldItem == newItem
        }
    }
) {
    companion object {
        const val VIEW_TYPE_BOOK_TITLE = 1
        const val VIEW_TYPE_BOOKS = 2

    }
    override fun getItemViewType(position: Int): Int {
        if(position == RecyclerView.NO_POSITION) return super.getItemViewType(position)
        return when(getItem(position)) {
            is BooksTitle -> VIEW_TYPE_BOOK_TITLE
            is BookList -> VIEW_TYPE_BOOKS
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            VIEW_TYPE_BOOK_TITLE -> BookTitleViewHolder(
                ItemBookTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            VIEW_TYPE_BOOKS -> BookListViewHolder(
                ItemBooksBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> NothingViewHolder(
                ItemNothingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)

        when(holder) {
            is BookTitleViewHolder -> {
                holder.onBind(item as BooksTitle)
            }
            is BookListViewHolder -> {

                holder.onBind(item as BookList)
            }
        }
    }
}