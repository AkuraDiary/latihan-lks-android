package com.example.latihanzendentaapinetcore.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

//"idUser": 2,
//"nama": "Panca Nugraha",
//"email": "ayamgoyeng@gmail.com",
//"username": "bangpan",
//"password": "awokawok",
//"alamat": "jalan jalan",
//"noTelp": "0812031423",
//"img":

@Parcelize
//@JsonClass(generateAdapter = true)
data class User (
  //  @SerializedName("idUser")
    val idUser: Int?,

    //@SerializedName("nama")
    val nama: String?,

    //@SerializedName("email")
    val email: String?,

    //@SerializedName("username")
    val username: String?,

    //@SerializedName("password")
    val password: String?,

    //@SerializedName("alamat")
    val alamat: String?,

    //@SerializedName("noTelp")
    val noTelp : String?,

    //@SerializedName("img")
    //val img : Byte?
): Parcelable