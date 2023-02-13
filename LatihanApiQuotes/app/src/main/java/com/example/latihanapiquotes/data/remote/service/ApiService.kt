package com.example.latihanapiquotes.data.remote.service

import com.example.latihanapiquotes.data.model.Quotes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("/quotes")
    fun getQuotes() : Call<Quotes>
}