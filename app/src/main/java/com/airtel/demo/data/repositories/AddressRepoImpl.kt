package com.airtel.demo.data.repositories

import com.airtel.demo.domain.models.AddressSuggestionData
import com.airtel.demo.domain.repositories.AddressRepo
import com.topgithub.demo.data.network.ApiService
import retrofit2.Response
import javax.inject.Inject

class AddressRepoImpl @Inject constructor(var networkService: ApiService) : AddressRepo  {
    override suspend fun getAddressSuggestion(queryString:String,
                             city:String): Response<AddressSuggestionData> {

        return  networkService.getAddressSuggestion(queryString, city);
    }
}