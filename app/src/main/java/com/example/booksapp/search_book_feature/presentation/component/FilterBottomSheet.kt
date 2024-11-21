package com.example.booksapp.search_book_feature.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.booksapp.R
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize14Style
import com.example.booksapp.ui.theme.fontSize20Style

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterBottomSheet(
    closeClick: () -> Unit,
    filterClick: (String) -> Unit,
    modalBottomSheetState: ModalBottomSheetState,
    content: @Composable () -> Unit
) {
    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = AppColors.white,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = AppColors.white,
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "필터",
                            style = fontSize20Style,
                            modifier = Modifier.padding(start = 12.dp)
                        )

                        IconButton(
                            onClick = closeClick,
                            modifier = Modifier.padding(end = 12.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_baseline_close_24),
                                contentDescription = "Close",
                                tint = AppColors.black
                            )
                        }
                    }
                }

                FilterOptionItem(
                    text = "제목",
                    onClick = { filterClick("title") }
                )

                FilterOptionItem(
                    text = "저자",
                    onClick = { filterClick("author") }
                )

                FilterOptionItem(
                    text = "출판사",
                    onClick = { filterClick("publisher") }
                )
            }
        },
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = AppColors.white,
    ) {
        content()
    }
}

@Composable
private fun FilterOptionItem(
    text: String,
    onClick: () -> Unit
) {
    Text(
        text = text,
        style = fontSize14Style,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(
                start = 12.dp,
                top = 12.dp,
                bottom = 12.dp
            )
    )
}