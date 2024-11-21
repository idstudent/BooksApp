package com.example.booksapp.core.domain.model

data class BookListResponse(
    val title: String?,
    val link: String?,
    val language: String?,
    val pubDate: String?,
    val imageUrl: String?,
    val totalResults: Int?,
    val startIndex: Int?,
    val itemsPerPage: Int?,
    val maxResults: Int?,
    val queryType: String?,
    val searchCategoryId: String?,
    val searchCategoryName: String?,
    val returnCode: String?,
    val returnMessage: String?,
    val item: List<BookItem>,
)
