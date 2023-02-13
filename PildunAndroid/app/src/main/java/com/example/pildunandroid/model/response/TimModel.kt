package com.example.pildunandroid.model.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TimModel(

    val id_tim: Int?,
    val nama_tim: String?,
    val img_url: String?
//{
//    "id_tim": 1,
//    "nama_tim": "Argentina",
//    "img_url": "https://cdn.countryflags.com/thumbs/argentina/flag-square-250.png"
//}
) : Parcelable
