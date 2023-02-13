package com.example.latihanzendentaapinetcore

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.latihanzendentaapinetcore.data.remote.RetrofitConfig
import com.example.latihanzendentaapinetcore.databinding.ActivityMainBinding
import com.example.latihanzendentaapinetcore.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding : ActivityMainBinding? = null
    private var listUser : List<User>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getAllDataUser()
        binding?.tvHome?.text = listUser.toString()
    }


    fun getAllDataUser(){

        val call: Call<List<User>> = RetrofitConfig.API_SERVICE.getAllUser()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_SHORT).show()
                if (response.isSuccessful){
                    listUser = response.body()
                    Log.d("LIST", response.body().toString())
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "CALL FAILED", Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}