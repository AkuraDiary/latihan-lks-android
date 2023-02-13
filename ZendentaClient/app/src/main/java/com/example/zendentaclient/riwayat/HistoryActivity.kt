package com.example.zendentaclient.riwayat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zendentaclient.data.DummyData
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.data.mapper.DataMapper
import com.example.zendentaclient.databinding.ActivityHistoryBinding
import com.example.zendentaclient.model.Appointment

class HistoryActivity : AppCompatActivity() {
    private var historyBinding: ActivityHistoryBinding? = null
    private var historyAdapter = HistoryAdapter.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(historyBinding?.root)

        //historyAdapter =  HistoryAdapter()
        historyBinding?.rvHistory?.apply {
            layoutManager = LinearLayoutManager(this@HistoryActivity)
            adapter = historyAdapter
        }

        //getHistoryUserData()

        observeHistoryData()
    }



    fun observeHistoryData(){
        //set The Value for rv
        Repository.pasienHistory.observe(this@HistoryActivity){
            historyAdapter?.setData(it)
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        historyBinding = null
        //historyAdapter = null
    }
}