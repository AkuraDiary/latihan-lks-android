package com.example.latihanzendentaapinetcore.data.remote.service

import androidx.lifecycle.LiveData
import com.example.latihanzendentaapinetcore.data.remote.response.UserListResponse
import com.example.latihanzendentaapinetcore.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/user")
     fun getAllUser() : Call<List<User>>//UserListResponse
}