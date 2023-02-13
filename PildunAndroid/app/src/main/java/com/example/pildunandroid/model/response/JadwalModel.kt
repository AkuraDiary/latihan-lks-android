package com.example.pildunandroid.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class JadwalModel(

    val id_jadwal: Int?,
    val nama_liga : String?,
    val tanggal_main : String?,
    val id_tim_satu : Int?,
    val id_tim_dua : Int?
//{
//    "id_jadwal": 1,
//    "nama_liga": "Round",
//    "tanggal_main": "10-12-2022",
//    "id_tim_satu": 1,
//    "id_tim_dua": 3
//}
):Parcelable
