package com.example.booksapp.core.data.remote.model

abstract class BaseResponse<T> {
    abstract fun mapper(): T
}