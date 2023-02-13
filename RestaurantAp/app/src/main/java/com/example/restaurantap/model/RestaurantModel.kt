package com.example.restaurantap.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RestaurantModel(
    val name : String,
    val address: String
) : Parcelable

object Restaurants{
    val listRestaurants: List<RestaurantModel> = listOf(
        RestaurantModel("RM Esselon", "Sawojajar"),
        RestaurantModel("Pak Agung", "Sawojajar"),
        RestaurantModel("RM Padang murah", "Sawojajar"),
 
    )

}