package com.example.booksapp.core.navigation

import android.net.Uri
import androidx.annotation.DrawableRes
import com.example.booksapp.R
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.BookFilterType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
sealed class NaviItem(
    val title: String?,
    @DrawableRes val iconResId: Int?,
    val route: String,
    val showBottomBar: Boolean = true
) {
    companion object {
        private val BOTTOM_NAV_ROUTES = listOf(
            "books",
            "best_seller",
            "my_books",
            "book_report"
        )

        fun shouldShowBottomBar(route: String?): Boolean {
            return when {
                route == null -> true
                BOTTOM_NAV_ROUTES.contains(route) -> true
                else -> false
            }
        }
    }

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
        route = "book_list_screen/{type}"
    ) {
        fun moveList(type: BookFilterType) = "book_list_screen/${type.name}"
    }

    data object SearchBook: NaviItem(
        title = "도서 검색",
        iconResId = null,
        route = "search_book_screen"
    ) {
        fun moveSearch() = "search_book_screen"
    }


    data object BookDetail: NaviItem(
        title = null,
        iconResId = null,
        route = "book_detail/{isbn}/{searchType}",
        showBottomBar = false
    ) {
        fun moveDetail(isbn: String, searchType: String) = "book_detail/${isbn}/${searchType}"
    }

    data object WriteReview: NaviItem(
        title = null,
        iconResId = null,
        route = "write_review/{book}",
        showBottomBar = false
    ) {
        fun moveWriteReview(book: Book) = "write_review/${Uri.encode(Json.encodeToString(book))}"
    }
}