package com.example.booksapp.core.navigation

import androidx.annotation.DrawableRes
import com.example.booksapp.R
import com.example.booksapp.data.constants.BookFilterType

sealed class NaviItem(val title: String, @DrawableRes val iconResId: Int?, val route: String) {
    data object Books: NaviItem(
        title = "도서",
        iconResId = R.drawable.ic_baseline_menu_book_24,
        route = "books"
    )

    data object BestSeller: NaviItem(
        title = "베스트셀러",
        iconResId = R.drawable.ic_baseline_local_fire_department_24,
        route = "best_seller"
    )

    data object MyBooks: NaviItem(
        title = "나의 책장",
        iconResId = R.drawable.ic_baseline_bookmark_24,
        route = "my_books"
    )

    data object BookReport: NaviItem(
        title = "독후감",
        iconResId = R.drawable.ic_baseline_edit_24,
        route = "book_report"
    )

    data object BookList: NaviItem(
        title = "도서 리스트",
        iconResId = null,
        route = "book_list_screen?type={type}"
    ) {
        fun moveList(type: BookFilterType) = "book_list_screen?type=${type.name}"
    }
}