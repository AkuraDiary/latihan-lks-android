package com.example.pildunandroid.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HasilModel(

    val id_hasil: Int?,
    val id_jadwal: Int?,
    val skor_satu: Int?,
    val skor_dua: Int?,
    val header_url: String?
//{
//    "id_hasil": 1,
//    "id_jadwal": 1,
//    "skor_satu": 3,
//    "skor_dua": 1,
//    "header_url": null
//}
) : Parcelable
