package com.example.pildunandroid.data.remote.service

import com.example.pildunandroid.model.response.HasilModel
import com.example.pildunandroid.model.response.JadwalModel
import com.example.pildunandroid.model.response.TimModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface ApiService {
    @Headers("Content-Type: application/json")
    @GET("jadwal")
    fun getALlJadwal(): Call<List<JadwalModel>>

    @GET("tim")
    fun getAllTim(): Call<List<TimModel>>

    @GET("hasil")
    fun getAllHasil(): Call<List<HasilModel>>


    @GET("tim/{id}")
    fun getTimById(@Path("id") id: Int): Call<TimModel>


    @GET("jadwal/{id}")
    fun getJadwalById(id: Int): Call<JadwalModel>

    @GET("hasil/{id}")
    fun getHasilById(id: Int): Call<HasilModel>
}