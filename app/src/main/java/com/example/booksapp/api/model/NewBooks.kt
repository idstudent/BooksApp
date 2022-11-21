package com.example.booksapp.api.model

interface Books

class NewBooks {
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
        val item: List<NewBooksItem>?,
    ) : BaseResponse<List<Books>>() {
        data class NewBooksItem(
            val itemId: Int?,
            val title: String?,
            val description: String?,
            val pubDate: String?,
            val priceStandard: Int?,
            val priceSales: Int?,
            val discountRate: String?,
            val saleStatus: String?,
            val mileage: String?,
            val mileageRate: String?,
            val coverSmallUrl: String?,
            val coverLargeUrl: String?,
            val categoryId: String?,
            val categoryName: String?,
            val publisher: String?,
            val customerReviewRank: Int?,
            val author: String?,
            val translator: String?,
            val isbn: String?,
            val link: String?,
            val mobileLink: String?,
            val additionalLink: String?,
            val reviewCount: Int?
        ) : Books

        override fun mapper(): List<Books> {
            val result = mutableListOf<Books>()

            item?.let { books ->
                result.add(BookList(
                    books.map {
                        Book(
                            id = it.itemId ?: 0,
                            title = it.title ?: "",
                            description = it.description ?: "",
                            date = it.pubDate ?: "",
                            priceStandard = it.priceStandard ?: 0,
                            priceSales = it.priceSales ?: 0,
                            discountRate = it.discountRate ?: "",
                            saleStatus = it.saleStatus ?: "",
                            mileage = it.mileage ?: "",
                            mileageRate = it.mileageRate ?: "",
                            coverSmallUrl = it.coverSmallUrl ?: "",
                            coverLargeUrl = it.coverLargeUrl ?: "",
                            categoryId = it.categoryId ?: "",
                            categoryName = it.categoryName ?: "",
                            publisher = it.publisher ?: "",
                            customerReviewRank = it.customerReviewRank ?: 0,
                            author = it.author ?: "",
                            translator = it.translator ?: "",
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


data class BooksTitle(
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
    val discountRate: String,
    val saleStatus: String,
    val mileage: String,
    val mileageRate: String,
    val coverSmallUrl: String,
    val coverLargeUrl: String,
    val categoryId: String,
    val categoryName: String,
    val publisher: String,
    val customerReviewRank: Int,
    val author: String,
    val translator: String,
    val link: String,
    val mobileLink: String,
    val reviewCount: Int
) : Books