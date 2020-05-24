package com.airtel.demo.domain.repositories

import com.airtel.demo.domain.models.AddressSuggestionData
import io.reactivex.Single

interface AddressRepo {
    fun getAddressSuggestion(queryString:String,
                             city:String): Single<AddressSuggestionData>
}