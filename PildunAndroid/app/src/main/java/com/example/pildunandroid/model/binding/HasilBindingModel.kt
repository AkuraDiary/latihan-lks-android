package com.example.pildunandroid.model.binding

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HasilBindingModel(
    var id_hasil: Int?,
    var jadwal : JadwalBindingModel?,
    var skor_satu  : Int?,
    var skor_dua : Int?,
    var header_url : String?
) : Parcelable