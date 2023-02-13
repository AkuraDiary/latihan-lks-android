package com.example.latihanapiquotes.data.remote

import com.example.latihanapiquotes.data.remote.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    val baseUrl = "https://quotable.io/"

    val http_client by lazy {
        OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES).build()
    }

    val retrofitBuilder by lazy {
        Retrofit.Builder().baseUrl(baseUrl).client(http_client)
            .addConverterFactory(GsonConverterFactory.create())
    }

    val API_SERVICE: ApiService by lazy {
        retrofitBuilder.build().create(ApiService::class.java)
    }
}