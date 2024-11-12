package com.example.booksapp.core.data.remote.model

class NothingResponse: BaseResponse<Unit>() {
    override fun mapper(): Unit {
        return Unit
    }

}