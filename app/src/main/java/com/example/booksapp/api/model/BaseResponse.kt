package com.example.booksapp.api.model

abstract class BaseResponse<T>() {
    abstract fun mapper(): T
}