package com.example.zendentaclient.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Appointment (
    var dokter: Dokter?,
    var date: String?,
    var treatment : Treatment?,
    var status : String? = ""
    ):Parcelable