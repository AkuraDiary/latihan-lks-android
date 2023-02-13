package com.example.pildunandroid.data


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.pildunandroid.data.remote.RetrofitConfig
import com.example.pildunandroid.model.binding.HasilBindingModel
import com.example.pildunandroid.model.binding.JadwalBindingModel
import com.example.pildunandroid.model.response.HasilModel
import com.example.pildunandroid.model.response.JadwalModel
import com.example.pildunandroid.model.response.TimModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object Repository {

    val listJadwalResponse =  MutableLiveData<List<JadwalModel>>()
    var listTimResponse = MutableLiveData<List<TimModel>>()
    val listHasilResponse = MutableLiveData<List<HasilModel>>()
    val listJadwalBinding = MutableLiveData<List<JadwalBindingModel>>()
    //val listHasilBinding =  MutableLiveData<List<HasilBindingModel>>()

    //var tempSelectedTim : TimModel? = null

    val message = MutableLiveData<String>()


    fun getAllJadwal(){
        val call: Call<List<JadwalModel>> = RetrofitConfig.API_SERVICE.getALlJadwal()
        call.enqueue(object  : Callback<List<JadwalModel>> {
            override fun onResponse(
                call: Call<List<JadwalModel>>,
                response: Response<List<JadwalModel>>
            ) {
                Log.d("Get All Jadwal", response.body().toString())
                if (response.isSuccessful){
                    Log.d("Get All Jadwal", response.body().toString())
                    listJadwalResponse.value = response.body()
                }else{
                    message.value = response.message()
                }
            }

            override fun onFailure(call: Call<List<JadwalModel>>, t: Throwable) {
                Log.d("get All Jadwal", t.message.toString())
                message.value = t.message.toString()
            }
        })
    }

    fun getAllTim(){
        val call: Call<List<TimModel>> = RetrofitConfig.API_SERVICE.getAllTim()
        call.enqueue(object  : Callback<List<TimModel>> {
            override fun onResponse(
                call: Call<List<TimModel>>,
                response: Response<List<TimModel>>
            ) {
                Log.d("Get All Tim", response.body().toString())
                if (response.isSuccessful){
                    listTimResponse.value = response.body()
                }else{
                    message.value = response.message()
                }
            }

            override fun onFailure(call: Call<List<TimModel>>, t: Throwable) {
                Log.d("Get All Tim", t.message.toString())
                message.value = t.message.toString()
            }
        })
    }

    fun getAllHasil(){
        val call: Call<List<HasilModel>> = RetrofitConfig.API_SERVICE.getAllHasil()
        call.enqueue(object  : Callback<List<HasilModel>>{
            override fun onResponse(
                call: Call<List<HasilModel>>,
                response: Response<List<HasilModel>>
            ) {
                Log.d("Get All Tim", response.body().toString())
                if (response.isSuccessful){
                    listHasilResponse.value = response.body()
                }else{
                    message.value = response.message()
                }
            }

            override fun onFailure(call: Call<List<HasilModel>>, t: Throwable) {
                Log.d("Get All Hasil", t.message.toString())
                message.value = t.message.toString()
            }

        })
    }

//    fun getTimById(id:Int){
//        val call: Call<TimModel> = RetrofitConfig.API_SERVICE.getTimById(id)
//        call.enqueue(object  : Callback<TimModel> {
//            override fun onResponse(
//                call: Call<TimModel>,
//                response: Response<TimModel>
//            ) {
//
//                if (response.isSuccessful){
//                    Log.d("Get All Jadwal From MA", response.body().toString())
//                    tempSelectedTim = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<TimModel>, t: Throwable) {
//                Log.d("getAllJadwal", t.message.toString())
//            }
//        })
//    }

//    companion object{
//        fun getIntance() = Repository()
//    }

}