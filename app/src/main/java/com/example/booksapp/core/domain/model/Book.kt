package com.example.booksapp.core.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val priceStandard: Int,
    val priceSales: Int,
    val discountRate: Double,
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
    val reviewCount: Int,
    val bookType: String
)