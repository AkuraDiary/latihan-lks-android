package com.example.zendentaclient.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Treatment (
    var id_treatment: Int?,
    var case_name : String?,
    var harga : Int? = 0
        ):Parcelable
