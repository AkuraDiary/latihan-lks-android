package com.example.zendentaclient.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dokter(
    var idDokter : Int? = 0,
    var namaDokter: String?,
    var hariPraktek : String?,
    var jamMulaiPraktek : String?,
    var jamAkhirPraktek : String?,
    var biayaPerSesi: Int?
) : Parcelable
