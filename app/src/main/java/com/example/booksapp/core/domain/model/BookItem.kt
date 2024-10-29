package com.example.booksapp.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class BookItem(
    @PrimaryKey
    val itemId: Int?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "pubDate")
    val pubDate: String?,
    @ColumnInfo(name = "priceStandard")
    val priceStandard: Int?,
    @ColumnInfo(name = "priceSales")
    val priceSales: Int?,
    @ColumnInfo(name = "discountRate")
    val discountRate: Int?,
    @ColumnInfo(name = "saleStatus")
    val saleStatus: String?,
    @ColumnInfo(name = "mileage")
    val mileage: String?,
    @ColumnInfo(name = "mileageRate")
    val mileageRate: String?,
    @ColumnInfo(name = "coverSmallUrl")
    val coverSmallUrl: String?,
    @ColumnInfo(name = "coverLargeUrl")
    val coverLargeUrl: String?,
    @ColumnInfo(name = "categoryId")
    val categoryId: String?,
    @ColumnInfo(name = "categoryName")
    val categoryName: String?,
    @ColumnInfo(name = "publisher")
    val publisher: String?,
    @ColumnInfo(name = "customerReviewRank")
    val customerReviewRank: Double?,
    @ColumnInfo(name = "author")
    val author: String?,
    @ColumnInfo(name = "translator")
    val translator: String?,
    @ColumnInfo(name = "isbn")
    val isbn: String?,
    @ColumnInfo(name = "link")
    val link: String?,
    @ColumnInfo(name = "mobileLink")
    val mobileLink: String?,
    @ColumnInfo(name = "additionalLink")
    val additionalLink: String?,
    @ColumnInfo(name = "reviewCount")
    val reviewCount: Int?,
    @ColumnInfo(name = "review")
    var review: String?
)