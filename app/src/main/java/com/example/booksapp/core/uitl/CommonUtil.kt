package com.example.booksapp.core.uitl

object CommonUtil {
    fun formatDate(date: String): String {
        return "${date.substring(0, 4)}-${date.substring(4, 6)}-${date.substring(6, 8)}"
    }
}