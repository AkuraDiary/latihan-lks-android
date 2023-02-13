package com.example.zendentaclient.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zendentaclient.data.mapper.DataMapper
import com.example.zendentaclient.data.source.remote.RetrofitConfig
import com.example.zendentaclient.data.source.remote.response.*
import com.example.zendentaclient.model.Appointment
import com.example.zendentaclient.model.Dokter
import com.example.zendentaclient.model.Treatment
import com.example.zendentaclient.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*


object Repository {
    //pendaftaran pasien fields
    internal val _dataSelectedDokter  = MutableLiveData<Dokter>()
    internal val _dataSelectedTreatment = MutableLiveData<Treatment>()
    internal val _selectedDate = MutableLiveData<String>()
    internal val _dataAppointment = MutableLiveData<Appointment>()

    private val _allDokter  = MutableLiveData<List<Dokter>>()
    val allDokter : LiveData<List<Dokter>> = _allDokter

    private val _allTreatments  = MutableLiveData<List<Treatment>>()
    val allTreatments : LiveData<List<Treatment>> = _allTreatments

    val dataSelectedDokter  : LiveData<Dokter> = _dataSelectedDokter
    val dataSelectedTreatment : LiveData<Treatment> = _dataSelectedTreatment
    val selectedDate : LiveData<String> = _selectedDate
    val dataAppointment: LiveData<Appointment> = _dataAppointment

    //pendaftaran pasien fields

    private val _loggedUser = MutableLiveData<User>()
    val loggedUser : LiveData<User> = _loggedUser

    private val _currentPasien = MutableLiveData<PasienResponse>()
    val currentPasien : LiveData<PasienResponse> = _currentPasien

    private val _pasienHistory = MutableLiveData<List<Appointment>>()
    val pasienHistory : LiveData<List<Appointment>> = _pasienHistory

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> = _message


    //auth methods
    fun doLoginUser(email : String, password : String){
        val user = User(
            null,
            email = email,
            null,
            password = password,

        )

        val call = RetrofitConfig.API_SERVICE.authUser(user)
        call.enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                //onRes
                Log.d("doLoginUser Calling", response.body().toString())
                if (response.isSuccessful){
                    _loggedUser.value = DataMapper.MapUserResponseToUser(response.body())

                    response.body()?.id_user?.let { getPasienByUserId(it) }
                }else{
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                //TODO("Not yet implemented")
                _message.value = t.message.toString()
                Log.d("doLoginUser Calling", t.message.toString())
            }

        })
    }
    //auth methods


    //GET METHOD
    fun getAllDokter(){
        val call = RetrofitConfig.API_SERVICE.getAllDokter()
        call.enqueue(object : Callback<List<DokterResponse>>{
            override fun onResponse(
                call: Call<List<DokterResponse>>,
                response: Response<List<DokterResponse>>
            ) {
                Log.d("getAllDokter Calling", response.body().toString())
                if (response.isSuccessful){
                    _allDokter.value = DataMapper.MapDokterResponseToModel(response.body())
                }else{
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<List<DokterResponse>>, t: Throwable) {
                //TODO("Not yet implemented")
                _message.value = t.message.toString()
                Log.d("doLoginUser Calling", t.message.toString())
            }

        })
    }

    fun getAllTreatment(){
        val call = RetrofitConfig.API_SERVICE.getAllTreatments()
        call.enqueue(object : Callback<List<Treatment>>{
            override fun onResponse(
                call: Call<List<Treatment>>,
                response: Response<List<Treatment>>
            ) {
                Log.d("getAllTreatment Calling", response.body().toString())
                if (response.isSuccessful){
                    _allTreatments.value = response.body()
                }else{
                    _message.value = response.message()
                }
            }

            override fun onFailure(call: Call<List<Treatment>>, t: Throwable) {
                //TODO("Not yet implemented")
                _message.value = t.message.toString()
                Log.d("doLoginUser Calling", t.message.toString())
            }

        })
    }

    fun getPasienByUserId(id_user : Int){
        val call = RetrofitConfig.API_SERVICE.getPasienByIdUser(id_user)
        call.enqueue(object  : Callback<PasienResponse>{
            override fun onResponse(
                call: Call<PasienResponse>,
                response: Response<PasienResponse>
            ) {
                Log.d("getPasienByUserId Calling", response.body().toString())
                if (response.isSuccessful){
                    _currentPasien.value = response.body()
                }

            }

            override fun onFailure(call: Call<PasienResponse>, t: Throwable) {
                Log.d("getPasienByUserId Calling", t.message.toString())
            }

        })
    }

    fun getAllPasienKunjunganHistory(id_pasien : Int){
        val call = RetrofitConfig.API_SERVICE.getAllKunjunganOfPatient(id_pasien)
        call.enqueue(object  : Callback<List<KunjunganResponse>>{
            override fun onResponse(
                call: Call<List<KunjunganResponse>>,
                response: Response<List<KunjunganResponse>>
            ) {
                Log.d("getAllPasienKunjunganHistory Calling", id_pasien.toString())
                Log.d("getAllPasienKunjunganHistory Calling", response.body().toString())
                if (response.isSuccessful){
                    _pasienHistory.value = DataMapper.MapKunjunganResponseToAppointment(response.body())
                }

            }

            override fun onFailure(call: Call<List<KunjunganResponse>>, t: Throwable) {
                Log.d("getPasienByUserId Calling", t.message.toString())
            }

        })
    }
    //GET METHOD

    //POST METHOD
    fun addNewKunjungan(data : KunjunganRequest){
        val call = RetrofitConfig.API_SERVICE.addNewKunjungan(data)
        call.enqueue(object  : Callback<KunjunganResponse>{
            override fun onResponse(
                call: Call<KunjunganResponse>,
                response: Response<KunjunganResponse>
            ) {
                Log.d("addNewKunjungan Calling", response.body().toString())
                _message.value = response.message()
//                if (response.isSuccessful){
//
//
//                    response.body()?.id_user?.let { getPasienByUserId(it) }
//                }else{
//                    _message.value = response.message()
//                }
            }

            override fun onFailure(call: Call<KunjunganResponse>, t: Throwable) {
                Log.d("addNewKunjungan Calling", t.message.toString())
                _message.value = t.message.toString()
            }

        })
    }
    //POST METHOD


    //UTILS METHOD
    fun formatDateToString(date : Date):String{
        val df = SimpleDateFormat("dd-MMM-yyyy")
        return df.format(date).toString()
    }
    fun formatIntToRp(value : Int) : String{
        val numberFormat = NumberFormat.getCurrencyInstance(Locale ("id", "ID"))
        return numberFormat.format(value)
    }
    fun formatJamPraktek(start : String, end : String) : String{
        return "${start} -  ${end}"
    }
    //UTILS METHOD
}