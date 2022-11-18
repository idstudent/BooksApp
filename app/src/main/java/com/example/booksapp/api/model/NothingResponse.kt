package com.example.booksapp.api.model

class NothingResponse: BaseResponse<Unit>() {
    override fun mapper(): Unit {
        return Unit
    }

}