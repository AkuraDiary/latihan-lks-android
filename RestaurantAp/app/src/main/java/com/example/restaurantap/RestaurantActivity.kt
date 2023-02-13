package com.example.restaurantap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantap.databinding.ActivityRestaurantBinding
import com.example.restaurantap.model.Restaurants

class RestaurantActivity : AppCompatActivity() {
    private var restaurantBinding: ActivityRestaurantBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurantBinding = ActivityRestaurantBinding.inflate(layoutInflater)
        setContentView(restaurantBinding?.root)

        var categoryName : String? = intent.getStringExtra("categoryName")
        restaurantBinding?.tvCategoryName?.text = categoryName

        restaurantBinding?.rvRestaurant.apply {
            this?.adapter = RestaurantAdapter(Restaurants.listRestaurants)
            this?.layoutManager = LinearLayoutManager(this@RestaurantActivity)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        restaurantBinding = null
    }
}