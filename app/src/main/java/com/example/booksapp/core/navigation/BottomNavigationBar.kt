package com.example.booksapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.booksapp.ui.theme.colorDD4500
import com.example.booksapp.ui.theme.white

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        NaviItem.Books,
        NaviItem.BestSeller,
        NaviItem.MyBooks,
        NaviItem.BookReport,
    )

    val selectedItem = remember { mutableStateOf(items[0]) }

    BottomNavigation(
        backgroundColor = white
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.iconResId), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = colorDD4500,
                unselectedContentColor = Color.Black,
                alwaysShowLabel = true,
                selected = selectedItem.value == item,
                onClick = {
                    navController.navigate(item.route) {
                        /*
                        네비게이션은 기본적으로 이도할때마다 화면스택이 쌓임 a,b,c 화면이 있을때
                        a화면을 보고 있다가 b화면 이동 a화면으로 다시 오면 a화면이 2개임
                        아래 옵션쓰면 화면이 기존있다면 생성x, 재사용해서 메모리낭비 방지, 화면도 1개로 됨
                         */
                        launchSingleTop = true
                    }
                },
            )
        }
    }
}