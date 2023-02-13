package com.example.latihanapiquotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.latihanapiquotes.adapter.RvQuotesAdapter
import com.example.latihanapiquotes.data.model.Result
import com.example.latihanapiquotes.data.model.Quotes
import com.example.latihanapiquotes.data.remote.RetrofitConfig
import com.example.latihanapiquotes.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var listQuotes: List<Result>? = null
    private var rvAdapter = RvQuotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        getAllQuotes()

        binding?.rvMain?.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = rvAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun getAllQuotes() {
        val call: Call<Quotes> = RetrofitConfig.API_SERVICE.getQuotes()
        call.enqueue(object : Callback<Quotes> {
            override fun onResponse(call: Call<Quotes>, response: Response<Quotes>) {
                Toast.makeText(this@MainActivity, "On Response", Toast.LENGTH_SHORT).show()
                if (response.isSuccessful){
                    listQuotes= response.body()?.results
                    //binding?.tvMain?.text = listQuotes.toString()
                    listQuotes?.let { rvAdapter.setData(it) }
                }
            }

            override fun onFailure(call: Call<Quotes>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Call Failed", Toast.LENGTH_SHORT).show()
            }

        })
    }
}