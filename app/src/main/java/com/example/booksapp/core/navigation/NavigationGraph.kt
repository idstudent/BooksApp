package com.example.booksapp.core.navigation


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.booksapp.books_feature.presentation.BookListScreen
import com.example.booksapp.books_feature.presentation.BooksScreen
import com.example.booksapp.books_feature.presentation.BooksViewModel
import com.example.booksapp.data.constants.BookFilterType


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NaviItem.Books.route
    ) {
        composable(route = NaviItem.Books.route) {
            val viewModel: BooksViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            LaunchedEffect(key1 = Unit) {
                viewModel.getBookList()
            }

            BooksScreen(
                uiState = uiState,
                moveList =  {
                    navController.navigate(NaviItem.BookList.moveList(it))
                }
            )
        }

        composable(
            route = NaviItem.BookList.route,
            arguments = listOf(
                navArgument("type") {
                    type = NavType.EnumType(BookFilterType::class.java)
                    defaultValue = BookFilterType.LOCAL
                }
            )
        ) {
            val viewModel: BooksViewModel = hiltViewModel()
            val uiState = viewModel.uiState

            val type = BookFilterType.valueOf(
                it.arguments?.getString("type")
                ?: BookFilterType.LOCAL.name
            )

            LaunchedEffect(key1 = Unit) {
                viewModel.getBookListByType(type)
            }

            BookListScreen(
                uiState = uiState,
                type = type,
                onBackClick = {
                    navController.popBackStack()
                }
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
