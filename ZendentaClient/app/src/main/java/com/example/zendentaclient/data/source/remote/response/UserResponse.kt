package com.example.zendentaclient.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    var id_user: Int?,
    val nama:String?,
    val email: String?,
    val username: String?,
    val password: String?,
    val alamat: String?,
    val no_telp: String?,
    //val img: String?
//{
//    "id_user": 1,
//    "nama": "sample string 2",
//    "email": "sample string 3",
//    "username": "sample string 4",
//    "password": "sample string 5",
//    "alamat": "sample string 6",
//    "no_telp": "sample string 7",
//    "img": "QEA="
//},
) : Parcelable
