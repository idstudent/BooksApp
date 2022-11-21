package com.example.booksapp.view.adapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.booksapp.R

object BookBindingAdapter {
    @BindingAdapter("app:bookImg")
    @JvmStatic
    fun showBookImage(imageView: AppCompatImageView, url : String?) {
        url?.let {
            Glide.with(imageView.context).load(it)
                .error(R.drawable.bg_not_image)
                .into(imageView)
        }
    }
}