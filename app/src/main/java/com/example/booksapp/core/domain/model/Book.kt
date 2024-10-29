package com.example.booksapp.core.domain.model

data class Book(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val priceStandard: Int,
    val priceSales: Int,
    val discountRate: Int,
    val saleStatus: String,
    val mileage: String,
    val mileageRate: String,
    val coverSmallUrl: String,
    val coverLargeUrl: String,
    val categoryId: String,
    val categoryName: String,
    val publisher: String,
    val customerReviewRank: Double,
    val author: String,
    val isbn: String,
    val reviewCount: Int
)