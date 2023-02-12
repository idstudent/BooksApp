package com.example.booksapp.presentation.view.adapter

import android.graphics.Paint
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.booksapp.R
import com.example.booksapp.presentation.view.util.numberFormat
import com.example.booksapp.presentation.view.util.won

object BookBindingAdapter {
    @BindingAdapter("bookImg")
    @JvmStatic
    fun showBookImage(imageView: AppCompatImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context).load(it)
                .error(R.drawable.bg_not_image)
                .into(imageView)
        }
    }

    @BindingAdapter("discount")
    @JvmStatic
    fun setDiscountVisible(view: AppCompatTextView, discount: Int?) {
        if (discount == null) {
            view.isVisible = false
        }
        view.isVisible = true
        view.text = "${discount}%"
    }

    @BindingAdapter("price")
    @JvmStatic
    fun setPrice(view: AppCompatTextView, price: Int?) {
        if (price == null) {
            view.isVisible = false
            return
        }
        view.isVisible = true
        view.text = price.numberFormat.won
    }

    @BindingAdapter("originalPrice")
    @JvmStatic
    fun setOriginalPrice(view: AppCompatTextView, price: Int?) {
        if (price == null) {
            view.isVisible = false
            return
        }
        view.isVisible = true
        view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        view.text = price.numberFormat.won
    }

    @BindingAdapter("status")
    @JvmStatic
    fun setStatus(view: AppCompatTextView, status: String?) {
        if (status == "" || status == null) {
            view.isVisible = false
            return
        }
        view.isVisible = true
        view.text = status
    }

    @BindingAdapter("dateFormat")
    @JvmStatic
    fun setDateFormat(view: AppCompatTextView, date: String?) {
        if (date == "" || date == null) {
            view.isVisible = false
            return
        }
        view.isVisible = true

        val dateText = "${date.substring(0, 4)}-${date.substring(4, 6)}-${date.substring(6, 8)}"
        view.text = dateText
    }

    @BindingAdapter("filterType")
    @JvmStatic
    fun setFilter(view: AppCompatTextView, type: String?) {
        if (type == "" || type == null) {
            Log.e("ljy", "11 $type")
            view.text = "제목"
            return
        }

        when (type) {
            "title" -> view.text = "제목"
            "author" -> view.text = "저자"
            "publisher" -> view.text = "출판사"
        }
    }
}