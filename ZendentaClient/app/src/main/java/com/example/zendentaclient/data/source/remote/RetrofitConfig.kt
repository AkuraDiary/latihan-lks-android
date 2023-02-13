package com.example.zendentaclient.data.source.remote

import com.example.zendentaclient.data.source.remote.service.ApiService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    val base_url = "http://192.168.100.193/ZendentaApi/api/"

    val mClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    val retrofitBuilder by lazy {
         Retrofit.Builder()
             .client(mClient)
             .baseUrl(base_url)
             .addConverterFactory(GsonConverterFactory.create())
             .build()
    }

    val API_SERVICE = retrofitBuilder.create(ApiService::class.java)

}