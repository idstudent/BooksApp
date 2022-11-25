package com.example.booksapp.api.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

interface Books

class BooksModel {
    data class Response(
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
        val item: List<BooksItem>,
    ) : BaseResponse<List<Books>>() {
        @Entity(tableName = "book")
        data class BooksItem(
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
            val review: String?
        ) : Books

        override fun mapper(): List<Books> {
            val result = mutableListOf<Books>()

            item.let { books ->
                result.add(BookList(
                    books.map {
                        Book(
                            id = it.itemId ?: 0,
                            title = it.title ?: "",
                            description = it.description ?: "",
                            date = it.pubDate ?: "",
                            priceStandard = it.priceStandard ?: 0,
                            priceSales = it.priceSales ?: 0,
                            discountRate = it.discountRate ?: 0,
                            saleStatus = it.saleStatus ?: "",
                            mileage = it.mileage ?: "",
                            mileageRate = it.mileageRate ?: "",
                            coverSmallUrl = it.coverSmallUrl ?: "",
                            coverLargeUrl = it.coverLargeUrl ?: "",
                            categoryId = it.categoryId ?: "",
                            categoryName = it.categoryName ?: "",
                            publisher = it.publisher ?: "",
                            customerReviewRank = it.customerReviewRank ?: 0.0,
                            author = it.author ?: "",
                            translator = it.translator ?: "",
                            isbn = it.isbn ?: "",
                            link = it.link ?: "",
                            mobileLink = it.mobileLink ?: "",
                            reviewCount = it.reviewCount ?: 0
                        )
                    }
                ))
            }
            return result
        }
    }

}

class Header : Books

data class BooksTitle(
    val filterType: String,
    val categoryId: String,
    val bookMainTitle: String
) : Books

data class BookList(
    val books: List<Book>
) : Books

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
    val translator: String,
    val isbn: String,
    val link: String,
    val mobileLink: String,
    val reviewCount: Int
) : Books