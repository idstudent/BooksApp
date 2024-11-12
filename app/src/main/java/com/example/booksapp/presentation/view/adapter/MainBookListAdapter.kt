package com.example.booksapp.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.core.data.remote.model.BookList
import com.example.booksapp.core.data.remote.model.Books
import com.example.booksapp.core.data.remote.model.BooksTitle
import com.example.booksapp.core.data.remote.model.Header
import com.example.booksapp.databinding.*
import com.example.booksapp.presentation.view.adapter.ViewHolder.BookHeaderViewHolder
import com.example.booksapp.presentation.view.adapter.ViewHolder.BookListViewHolder
import com.example.booksapp.presentation.view.adapter.ViewHolder.BookTitleViewHolder
import com.example.booksapp.presentation.view.adapter.ViewHolder.NothingViewHolder

class MainBookListAdapter : ListAdapter<Books, RecyclerView.ViewHolder>(
    object: DiffUtil.ItemCallback<Books>() {
        override fun areItemsTheSame(oldItem: Books, newItem: Books): Boolean {
            return when {
                oldItem is Header && newItem is Header -> {
                    oldItem != newItem
                }
                oldItem is BooksTitle && newItem is BooksTitle -> {
                    oldItem.categoryId == newItem.categoryId
                }
                oldItem is BookList && newItem is BookList -> {
                    oldItem.books == newItem.books
                }
                else -> false
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Books, newItem: Books): Boolean {
            return when {
                oldItem is Header && newItem is Header -> {
                    oldItem != newItem
                }
                oldItem is BooksTitle && newItem is BooksTitle -> {
                    oldItem.categoryId == newItem.categoryId
                }
                oldItem is BookList && newItem is BookList -> {
                    oldItem.books == newItem.books
                }
                else -> false
            }
        }
    }
) {
    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_BOOK_TITLE = 2
        const val VIEW_TYPE_BOOKS = 3
    }
    override fun getItemViewType(position: Int): Int {
        if(position == RecyclerView.NO_POSITION) return super.getItemViewType(position)
        return when(getItem(position)) {
            is Header -> VIEW_TYPE_HEADER
            is BooksTitle -> VIEW_TYPE_BOOK_TITLE
            is BookList -> VIEW_TYPE_BOOKS
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {

            VIEW_TYPE_HEADER -> BookHeaderViewHolder(
                ItemBookHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

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
            is BookHeaderViewHolder -> {}
            is BookTitleViewHolder -> {
                holder.onBind(item as BooksTitle)
            }
            is BookListViewHolder -> {
                holder.onBind(item as BookList)
            }
        }
    }
}