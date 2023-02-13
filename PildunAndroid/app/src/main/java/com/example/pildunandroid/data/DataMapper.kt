package com.example.pildunandroid.data

import android.util.Log
import com.example.pildunandroid.model.binding.HasilBindingModel
import com.example.pildunandroid.model.binding.JadwalBindingModel
import com.example.pildunandroid.model.response.HasilModel
import com.example.pildunandroid.model.response.JadwalModel
import com.example.pildunandroid.model.response.TimModel
import java.text.SimpleDateFormat

object DataMapper {



    fun MapResponseTimAndJadwal( jadwalResponse : List<JadwalModel>, timResponse : List<TimModel> ) : List<JadwalBindingModel>{
        Log.d("Data Mapper" , jadwalResponse.toString())
        Log.d("Data Mapper" , timResponse.toString())
        val result = ArrayList<JadwalBindingModel>()
        //val item_date_formatter = SimpleDateFormat("yyyy-M-dd")

        for(item in jadwalResponse){
            //Log.d("Data Mapper" , item.tanggal_main!!.substringBefore("T"))
            var jadwalBinding = JadwalBindingModel(
                id_jadwal = item.id_jadwal,
                nama_liga = item.nama_liga,
                tanggal_main = item.tanggal_main?.substringBefore("T"),
                null,
                null,
            )
            val timSatu = timResponse.find {
                it.id_tim == item.id_tim_satu
            }
            val timDua = timResponse.find {
                it.id_tim == item.id_tim_dua
            }

            jadwalBinding.apply {
                tim_satu = timSatu
                tim_dua = timDua
            }

            Log.d("Data Mapper" , jadwalBinding.toString())
            result.add(jadwalBinding)
        }
        return result
    }

    fun MapResponseJadwalAndHasil(hasilResponse : List<HasilModel>, jadwalBindingModel : List<JadwalBindingModel> ) : List<HasilBindingModel>{
        Log.d("Data Mapper" , hasilResponse.toString())
        Log.d("Data Mapper" , jadwalBindingModel.toString())
        val result = ArrayList<HasilBindingModel>()
        for(item in hasilResponse){
            var hasilBinding = HasilBindingModel(
                id_hasil = item.id_hasil,
                jadwal = null,
                skor_satu = item.skor_satu,
                skor_dua = item.skor_dua,
                header_url = item.header_url
            )
            val jadwalData = jadwalBindingModel.find {
                it.id_jadwal == item.id_jadwal
            }

            hasilBinding.jadwal = jadwalData

            Log.d("Data Mapper" , hasilBinding.toString())
            result.add(hasilBinding)
        }
        return result
    }
}