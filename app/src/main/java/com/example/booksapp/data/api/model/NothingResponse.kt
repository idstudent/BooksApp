package com.example.booksapp.data.api.model

class NothingResponse: BaseResponse<Unit>() {
    override fun mapper(): Unit {
        return Unit
    }

}