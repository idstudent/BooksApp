package com.example.booksapp.presentation.compose.bottomNavigation

import com.example.booksapp.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("도서", R.drawable.ic_baseline_menu_book_24,"도서")
    object BestSeller: BottomNavItem("베스트셀러", R.drawable.ic_baseline_local_fire_department_24,"베스트셀러")
    object MyBook: BottomNavItem("나의 책장", R.drawable.ic_baseline_bookmark_24,"나의 책장")
    object BookReport: BottomNavItem("독후감", R.drawable.ic_baseline_edit_24,"독후감")
}
