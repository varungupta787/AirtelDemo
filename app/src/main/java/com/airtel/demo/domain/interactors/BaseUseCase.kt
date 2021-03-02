package com.airtel.demo.domain.interactors

import com.airtel.demo.data.network.NetworkResponseWrapper
import retrofit2.Response

abstract class BaseUseCase {


    protected fun <T> executeUseCase(response: Response<T>): NetworkResponseWrapper<T> {

        if (response.isSuccessful) {
            return NetworkResponseWrapper.NetworkSuccess(response.body())
        } else {
            return NetworkResponseWrapper.NetworkError(response.message())
        }

    }
}
