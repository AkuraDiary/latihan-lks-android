package com.example.latihanzendentaapinetcore.data.remote

import com.example.latihanzendentaapinetcore.data.remote.service.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitConfig {

    //"http://10.0.2.2:5000/"
    val base_url = "https://192.168.137.1:5001/zendenta/api/"


    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val client by lazy {
        OkHttpClient.Builder()
            //.addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    private val moshiConverterFactory by lazy {
        Moshi.Builder()
            //.add(KotlinJsonAdapterFactory())
            .build()
    }

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .baseUrl(base_url)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())//MoshiConverterFactory.create())
    }

    val API_SERVICE: ApiService by lazy {
        retrofitBuilder.build().create(ApiService::class.java)
    }
}