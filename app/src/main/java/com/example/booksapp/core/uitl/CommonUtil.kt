package com.example.booksapp.core.uitl

import com.example.booksapp.presentation.view.util.DECIMAL_FORMAT

fun formatDate(date: String): String {
    return "${date.substring(0, 4)}-${date.substring(4, 6)}-${date.substring(6, 8)}"
}
inline val Int.numberFormat: String
    get() = DECIMAL_FORMAT.format(this)