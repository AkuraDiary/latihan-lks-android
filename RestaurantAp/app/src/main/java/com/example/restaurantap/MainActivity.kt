package com.example.restaurantap

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.restaurantap.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var mainBinding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding?.root)

        mainBinding?.apply {
            btnItalianRestaurant.setOnClickListener{
                openSubCategory("Italian")
            }

            btnchineseeRestaurant.setOnClickListener {
                openSubCategory("Chinesee")
            }

            btnIndianRestaurant.setOnClickListener {
                openSubCategory("Indian")
            }

            btnGreekRestaurant.setOnClickListener {
                openSubCategory("Greek")
            }

        }
    }

    fun openSubCategory(categoryName : String){
        intent = Intent(this@MainActivity, RestaurantActivity::class.java)
        intent.putExtra("categoryName", categoryName)
        startActivity(intent)
    }
    override fun onDestroy() {
        super.onDestroy()
        this.mainBinding = null
    }
}