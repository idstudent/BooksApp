package com.example.booksapp.core.uitl

val DECIMAL_FORMAT = java.text.DecimalFormat("###,###")

fun formatDate(date: String): String {
    return "${date.substring(0, 4)}-${date.substring(4, 6)}-${date.substring(6, 8)}"
}
inline val Int.numberFormat: String
    get() = DECIMAL_FORMAT.format(this)