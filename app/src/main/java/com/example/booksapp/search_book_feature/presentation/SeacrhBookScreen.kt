package com.example.booksapp.search_book_feature.presentation

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Checkbox
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.booksapp.R
import com.example.booksapp.ui.theme.AppColors
import com.example.booksapp.ui.theme.fontSize14Style
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.booksapp.search_book_feature.presentation.component.FilterBottomSheet
import com.example.booksapp.search_book_feature.presentation.component.PagingBookContent
import com.example.booksapp.ui.theme.fontSize20Style
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchBookScreen(
    viewModel: SearchBookListViewModel,
    onBackClick: () -> Unit,
    onItemClick: (String, String) -> Unit
) {
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val books = viewModel.uiState.searchResultBook.collectAsLazyPagingItems()

    var searchText by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    var filter by remember { mutableStateOf("title") }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )

    val coroutineScope = rememberCoroutineScope()

    fun hideKeyboard() {
        focusManager.clearFocus()
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val activity = context as Activity
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }

    // 바텀시트가 떠있는 경우 뒤로가기 했을때 바텀시트만 닫히도록
    val isSheetOpen = bottomSheetState.isVisible

    BackHandler(enabled = isSheetOpen) {
        coroutineScope.launch {
            bottomSheetState.hide()
        }
    }

    FilterBottomSheet(
        closeClick = {
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        },
        filterClick = {
            filter = it
            coroutineScope.launch {
                bottomSheetState.hide()
            }
        },
        modalBottomSheetState = bottomSheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(
                    modifier = Modifier
                        .padding(start = 14.dp)
                        .clickable { onBackClick() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_btn_title_back),
                        contentDescription = "back"
                    )
                }

                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 12.dp),
                    textStyle = TextStyle(
                        fontSize = 14.sp,
                        color = AppColors.black
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    decorationBox = { innerTextField ->
                        Box {
                            if (searchText.isEmpty()) {
                                Text(
                                    text = "검색어를 입력해주세요",
                                    style = fontSize14Style.copy(color = AppColors.color565656),
                                    fontSize = 14.sp
                                )
                            }
                            innerTextField()
                        }
                    }
                )

                Box(
                    modifier = Modifier
                        .padding(end = 20.dp)
                        .clickable {
                            viewModel.getSearchBookList(
                                title = searchText,
                                filter = filter,
                                searchType = if (isChecked) "foreign" else "book",
                            )
                        }
                        .border(
                            width = 0.4.dp,
                            color = AppColors.black,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(
                            horizontal = 12.dp,
                            vertical = 6.dp
                        )
                ) {
                    Text(
                        text = "검색",
                        style = fontSize14Style
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, end = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Row(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable { isChecked = !isChecked },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = null,
                        modifier = Modifier.size(18.dp),
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "외국 도서 보기",
                        style = fontSize14Style
                    )
                }

                Text(
                    text = when (filter) {
                        "title" -> {
                            "제목"
                        }
                        "author" -> {
                            "저자"
                        }
                        else -> {
                            "출판사"
                        }
                    },

                    style = fontSize14Style,
                    modifier = Modifier
                        .clickable {
                            hideKeyboard()
                            coroutineScope.launch {
                                bottomSheetState.show()
                            }
                        },
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp),
            ){
                if(books.itemCount > 0) {
                    PagingBookContent(
                        pagingBookList = books,
                        type = if (isChecked) "foreign" else "book",
                        onItemClick = onItemClick
                    )
                }else {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "검색 결과가 없어요!",
                            style = fontSize20Style,
                        )
                    }
                }
            }
        }
    }
}