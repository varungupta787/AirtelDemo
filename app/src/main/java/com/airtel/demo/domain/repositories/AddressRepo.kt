package com.airtel.demo.domain.repositories

import com.airtel.demo.domain.models.AddressSuggestionData
import retrofit2.Response

interface AddressRepo {
    suspend fun getAddressSuggestion(queryString:String,
                             city:String): Response<AddressSuggestionData>
}