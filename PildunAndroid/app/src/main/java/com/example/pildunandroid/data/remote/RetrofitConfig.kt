package com.example.pildunandroid.data.remote

import com.example.pildunandroid.data.remote.service.ApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    val base_url = "http://192.168.1.73//pildunpildun/api/"
    val http_client by lazy {
        OkHttpClient.Builder()

            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .client(http_client)
            .addConverterFactory(GsonConverterFactory.create())

    }

    val API_SERVICE: ApiService by lazy {
        retrofitBuilder.build().create(ApiService::class.java)
    }
}