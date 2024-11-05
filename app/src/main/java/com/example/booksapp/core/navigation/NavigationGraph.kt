package com.example.booksapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.booksapp.book_detail_feature.presentation.BookDetailScreen
import com.example.booksapp.book_detail_feature.presentation.BookDetailViewModel
import com.example.booksapp.books_feature.presentation.BookListScreen
import com.example.booksapp.books_feature.presentation.BooksScreen
import com.example.booksapp.books_feature.presentation.BooksViewModel
import com.example.booksapp.data.constants.BookFilterType


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NaviItem.Books.route,
    ) {
        composable(route = NaviItem.Books.route) {
            val viewModel: BooksViewModel = hiltViewModel()

            BooksScreen(
                viewModel = viewModel,
                moveList =  {
                    navController.navigate(NaviItem.BookList.moveList(it))
                },
                onItemClick = { isbn, searchType ->
                    navController.navigate(NaviItem.BookDetail.moveDetail(isbn, searchType)) {
                        launchSingleTop = true
                        popUpTo(NaviItem.Books.route)
                    }
                }

            )
        }

        composable(
            route = NaviItem.BookList.route,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                    defaultValue = BookFilterType.LOCAL.name
                }
            )
        ) { backStackEntry ->
            val viewModel: BooksViewModel = hiltViewModel()

            val type = try {
                BookFilterType.valueOf(
                    backStackEntry.arguments?.getString("type")
                        ?: BookFilterType.LOCAL.name
                )
            } catch (e: Exception) {
                BookFilterType.LOCAL
            }


            BookListScreen(
                viewModel = viewModel,
                type = type,
                onBackClick = {
                    navController.popBackStack()
                },
                onItemClick = { isbn, searchType ->
                    navController.navigate(NaviItem.BookDetail.moveDetail(isbn, searchType))
                }
            )
        }

        composable(
            route = NaviItem.BookDetail.route,
            arguments = listOf(
                navArgument("isbn") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("searchType") {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val viewModel: BookDetailViewModel = hiltViewModel()

            val isbn = backStackEntry.arguments?.getString("isbn") ?: ""
            val searchType = backStackEntry.arguments?.getString("searchType") ?: ""

            BookDetailScreen(viewModel = viewModel, isbn = isbn, searchType = searchType)
        }

        composable(route = NaviItem.BestSeller.route) {

        }

        composable(route = NaviItem.MyBooks.route) {
            // MyBooks 화면
        }

        composable(route = NaviItem.BookReport.route) {
            // BookReport 화면
        }
    }
}