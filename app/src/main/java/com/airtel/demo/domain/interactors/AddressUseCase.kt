package com.airtel.demo.domain.interactors

import androidx.lifecycle.LiveData
import com.airtel.demo.data.network.NetworkResponseWrapper
import com.airtel.demo.domain.models.AddressSuggestionData
import com.airtel.demo.domain.repositories.AddressRepo
import javax.inject.Inject

open class AddressUseCase @Inject constructor(
        var addressRepo: AddressRepo
) : BaseUseCase() {

    suspend fun execute(queryString: String, city:String): NetworkResponseWrapper<AddressSuggestionData> {
        return executeUseCase(addressRepo.getAddressSuggestion(queryString, city))
    }

}