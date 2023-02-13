package com.example.zendentaclient.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: Int?,
    val email: String?,
    val username: String?,
    val password: String?,

) : Parcelable