package com.example.booksapp.core.data.mapper

import com.example.booksapp.core.domain.model.Book
import com.example.booksapp.core.domain.model.BookItem
import com.example.booksapp.core.domain.model.BookListResponse

fun BookItem.toBook(bookType: String): Book {
    return Book(
        id = itemId ?: 0,
        title = title ?: "",
        description = description ?: "",
        date = pubDate ?: "",
        priceStandard = priceStandard ?: 0,
        priceSales = priceSales ?: 0,
        discountRate = discountRate ?: 0.0,
        saleStatus = saleStatus ?: "",
        mileage = mileage ?: "",
        mileageRate = mileageRate ?: "",
        coverSmallUrl = coverSmallUrl ?: "",
        coverLargeUrl = coverLargeUrl ?: "",
        categoryId = categoryId ?: "",
        categoryName = categoryName ?: "",
        publisher = publisher ?: "",
        customerReviewRank = customerReviewRank ?: 0.0,
        author = author ?: "",
        isbn = isbn ?: "",
        bookType = bookType,
        review = review ?: ""
    )
}

fun BookListResponse.toBook(bookType: String): List<Book> {
    return this.item.map { bookItem ->
        bookItem.toBook(bookType)
    }
}