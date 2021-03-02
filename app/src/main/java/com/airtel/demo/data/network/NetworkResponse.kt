package com.airtel.demo.data.network

sealed class NetworkResponseWrapper<T>(status: NetworkResponseStatus) {
    data class NetworkSuccess<T>(val data:T?): NetworkResponseWrapper<T>(NetworkResponseStatus.SUCCESS)
    data class NetworkError<Nothing>(val errorMsg:String?): NetworkResponseWrapper<Nothing>(NetworkResponseStatus.ERROR)
    sealed class HttpErrors<Nothing> : NetworkResponseWrapper<Nothing>(NetworkResponseStatus.ERROR) {
        data class InternalServerError(val errorMsg: String?):HttpErrors<Nothing>()
        data class ResourceNotFound(val errorMsg: String?):HttpErrors<Nothing>()
        data class BadGateWay(val errorMsg: String?):HttpErrors<Nothing>()
    }
}

enum class NetworkResponseStatus {
    SUCCESS,
    ERROR
}
