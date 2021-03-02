package com.topgithub.demo.data.network

import com.airtel.demo.domain.models.AddressSuggestionData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("compassLocation/rest/address/autocomplete")
    suspend fun getAddressSuggestion(@Query("queryString") queryString:String,
                          @Query("city") city:String): Response<AddressSuggestionData>
}
