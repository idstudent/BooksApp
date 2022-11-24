package com.example.booksapp.view.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.api.model.BooksModel
import com.example.booksapp.databinding.ItemViewpagerBookBinding
import com.example.booksapp.view.BookDetailActivity
import com.example.booksapp.view.util.setOnSingleClickListener

class BooksCarouselAdapter :
    ListAdapter<BooksModel.Response.BooksItem, BooksCarouselAdapter.BookViewHolder>(
        object : DiffUtil.ItemCallback<BooksModel.Response.BooksItem>() {
            override fun areItemsTheSame(
                oldItem: BooksModel.Response.BooksItem,
                newItem: BooksModel.Response.BooksItem
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BooksModel.Response.BooksItem,
                newItem: BooksModel.Response.BooksItem
            ): Boolean {
                return oldItem == newItem
            }

        }
    ){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder(
            binding = ItemViewpagerBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int {
        return if(super.getItemCount() > 6) 6
        else super.getItemCount()
    }

    class BookViewHolder(
        private val binding: ItemViewpagerBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.run {
                root.setOnSingleClickListener {
                    item?.let {
                        val intent = Intent(binding.root.context, BookDetailActivity::class.java)
                        intent.putExtra("isbn", it.isbn)
                        if(it.categoryId == "200") {
                            intent.putExtra("searchType", "foreign")
                        }
                        this.root.context.startActivity(intent)
                    }
                }
            }
        }

        fun onBind(item: BooksModel.Response.BooksItem) {
            binding.item = item
        }
    }
}