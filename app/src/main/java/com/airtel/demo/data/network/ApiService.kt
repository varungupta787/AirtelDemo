package com.topgithub.demo.data.network

import com.airtel.demo.domain.models.AddressSuggestionData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("compassLocation/rest/address/autocomplete")
    fun getAddressSuggestion(@Query("queryString") queryString:String,
                          @Query("city") city:String): Single<AddressSuggestionData>
}
