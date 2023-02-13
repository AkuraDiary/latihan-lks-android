package com.example.zendentaclient.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PasienResponse (

    var id_pasien : Int?,
    var id_user: Int?,
//{
//    "id_pasien": 1,
//    "id_user": 2,
//    "next_appointment": "2022-12-23T11:05:05.2655873+07:00",
//    "last_appointment": "2022-12-23T11:05:05.2665846+07:00"
//},
        ) : Parcelable
