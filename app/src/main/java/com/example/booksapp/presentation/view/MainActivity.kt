package com.example.booksapp.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.booksapp.R
import com.example.booksapp.core.MainScreen
import com.example.booksapp.databinding.ActivityMainBinding
import com.example.booksapp.ui.theme.BooksAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setWindowAnimations(android.R.style.Animation_Activity)  // 추가

        setContent {
            BooksAppTheme {
                MainScreen(navController = rememberNavController())
            }
        }
    }
}