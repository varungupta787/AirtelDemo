package com.airtel.demo.presentation.di.modules


import com.airtel.demo.presentation.di.scope.ApplicationScope
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.topgithub.demo.data.network.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    val BASE_URL = "https://digi-api.airtel.in/"

    @Provides
    @ApplicationScope
    fun getGsonConverterFactory(): Gson = GsonBuilder().create()


    @Provides
    @ApplicationScope
    fun getInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @ApplicationScope
    fun getApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @ApplicationScope
    fun getRetrofit(
        gson: Gson, okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson)).build()
    }

    @Provides
    @ApplicationScope
    fun getOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addNetworkInterceptor(interceptor).connectTimeout(
            120,
            TimeUnit.SECONDS
        ).build()
    }
}
