package com.example.booksapp.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.booksapp.data.api.model.BooksModel
import com.example.booksapp.databinding.ItemViewpagerBookBinding
import com.example.booksapp.presentation.view.adapter.ViewHolder.CarouselBookViewHolder

class BooksCarouselAdapter :
    ListAdapter<BooksModel.Response.BooksItem, CarouselBookViewHolder>(
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselBookViewHolder {
        return CarouselBookViewHolder(
            binding = ItemViewpagerBookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CarouselBookViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    override fun getItemCount(): Int {
        return if(super.getItemCount() > 6) 6
        else super.getItemCount()
    }
}