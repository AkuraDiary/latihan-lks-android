package com.example.latihanzendentaapinetcore.data.remote.response

import android.os.Parcelable
import com.example.latihanzendentaapinetcore.model.User
import kotlinx.parcelize.Parcelize
@Parcelize
data class UserListResponse (
    val listUser : List<User>
        ):Parcelable
