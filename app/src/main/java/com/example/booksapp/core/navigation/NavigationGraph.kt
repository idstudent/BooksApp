package com.example.booksapp.core.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.booksapp.best_seller_book_feature.presentation.BestSellerBookScreen
import com.example.booksapp.best_seller_book_feature.presentation.BestSellerBookViewModel
import com.example.booksapp.book_detail_feature.presentation.BookDetailScreen
import com.example.booksapp.book_detail_feature.presentation.BookDetailViewModel
import com.example.booksapp.book_like_feature.presentation.BookLikeScreen
import com.example.booksapp.book_like_feature.presentation.BookLikeViewModel
import com.example.booksapp.books_feature.presentation.BookListScreen
import com.example.booksapp.books_feature.presentation.BooksScreen
import com.example.booksapp.books_feature.presentation.BooksViewModel
import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.uitl.BookFilterType
import com.example.booksapp.search_book_feature.presentation.SearchBookListViewModel
import com.example.booksapp.search_book_feature.presentation.SearchBookScreen
import com.example.booksapp.write_review_feature.presentation.WriteReviewScreen
import kotlinx.serialization.json.Json


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
                moveSearchBook = {
                    navController.navigate(NaviItem.SearchBook.moveSearch())
                },
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
            route = NaviItem.SearchBook.route
        ) {
            val viewModel: SearchBookListViewModel = hiltViewModel()

            SearchBookScreen(
                viewModel = viewModel,
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

            BookDetailScreen(
                viewModel = viewModel,
                isbn = isbn,
                searchType = searchType,
                moveWriteReview = {
                    navController.navigate(NaviItem.WriteReview.moveWriteReview(it))
                }
            )
        }

        composable(route = NaviItem.BestSeller.route) {
            val viewModel: BestSellerBookViewModel = hiltViewModel()

            BestSellerBookScreen(
                viewModel = viewModel,
                moveList = {
                    navController.navigate(NaviItem.BookList.moveList(it))
                },
                onItemClick = { isbn, searchType ->
                    navController.navigate(NaviItem.BookDetail.moveDetail(isbn, searchType))
                }
            )
        }

        composable(route = NaviItem.MyBooks.route) {
            val viewModel: BookLikeViewModel = hiltViewModel()

            BookLikeScreen(
                viewModel = viewModel,
                onItemClick = { isbn, searchType ->
                    navController.navigate(NaviItem.BookDetail.moveDetail(isbn, searchType))
                }
            )
        }

        composable(
            route = NaviItem.WriteReview.route,
            arguments = listOf(
                navArgument("book") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val bookString = backStackEntry.arguments?.getString("book")
            val book = bookString?.let { Uri.decode(it) }?.let { Json.decodeFromString<Book>(it) }

            if (book != null) {
                WriteReviewScreen(
                    book = book,
                )
            }
        }
        composable(route = NaviItem.BookReport.route) {
            // BookReport 화면
        }
    }
}