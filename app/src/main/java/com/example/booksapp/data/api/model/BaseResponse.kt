package com.example.booksapp.data.api.model

abstract class BaseResponse<T> {
    abstract fun mapper(): T
}