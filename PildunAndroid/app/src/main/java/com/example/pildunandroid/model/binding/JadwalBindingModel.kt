package com.example.pildunandroid.model.binding

import android.os.Parcelable
import com.example.pildunandroid.model.response.TimModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class JadwalBindingModel(
    var id_jadwal: Int?,
    var nama_liga: String?,
    val tanggal_main: String?,

    var tim_satu: TimModel?,

    var tim_dua: TimModel?,

    ):Parcelable