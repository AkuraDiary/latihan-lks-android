package com.example.zendentaclient.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DokterResponse (

        var id_dokter : Int?,
        var nama : String?,
        var username : String?,
        var id_klinik : Int?

//{
//    "id_dokter": 1,
//    "nama": "sample string 2",
//    "username": "sample string 3",
//    "password": "sample string 4",
//    "id_klinik": 1,
//    "img": "QEA="
//},
        ) : Parcelable
