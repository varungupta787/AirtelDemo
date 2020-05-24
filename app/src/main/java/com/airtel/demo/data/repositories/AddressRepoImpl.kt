package com.airtel.demo.data.repositories

import com.airtel.demo.domain.models.AddressSuggestionData
import com.airtel.demo.domain.repositories.AddressRepo
import com.topgithub.demo.data.network.ApiService
import io.reactivex.Single
import javax.inject.Inject

class AddressRepoImpl @Inject constructor(var networkService: ApiService) : AddressRepo  {
    override fun getAddressSuggestion(queryString:String,
                             city:String): Single<AddressSuggestionData> {

        return  networkService.getAddressSuggestion(queryString, city);
    }
}