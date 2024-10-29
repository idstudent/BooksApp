package com.example.booksapp.core.navigation


import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.booksapp.books_feature.presentation.BooksScreen
import com.example.booksapp.books_feature.presentation.BooksViewModel


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NaviItem.Books.route
    ) {
        composable(route = NaviItem.Books.route) {
            val viewModel: BooksViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            BooksScreen(
                uiState = uiState
            )
        }

        composable(route = NaviItem.BestSeller.route) {
            // 여기에 BestSeller 화면 Composable
        }

        composable(route = NaviItem.MyBooks.route) {
            // 여기에 MyBooks 화면 Composable
        }

        composable(route = NaviItem.BookReport.route) {
            // 여기에 BookReport 화면 Composable
        }
    }
}
