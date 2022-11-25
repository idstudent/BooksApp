package com.example.booksapp.view.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.databinding.ItemBookHeaderBinding
import com.example.booksapp.view.SearchActivity
import com.example.booksapp.view.util.setOnSingleClickListener

class BookHeaderViewHolder(
    binding : ItemBookHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.run {
            ivSearch.setOnSingleClickListener {
                val intent = Intent(root.context, SearchActivity::class.java)
                root.context.startActivity(intent)
            }
        }
    }
}