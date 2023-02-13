package com.example.zendentaclient

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.zendentaclient.data.DummyData
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.data.source.session.UserPreferences
import com.example.zendentaclient.databinding.ActivityMainBinding
import com.example.zendentaclient.model.User
import com.example.zendentaclient.pendaftaran.PendaftaranActivity
import com.example.zendentaclient.riwayat.HistoryActivity

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null
    private lateinit var userPreferences : UserPreferences
    private var loggedUser : User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPreferences = UserPreferences(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        loggedUser = userPreferences.getUserSession()

        binding?.tvNamaUser?.text = loggedUser?.username

        binding?.fabAddAppointment?.setOnClickListener {
            startNewProsesPendaftaran()
        }

        setToHistory()
        setNextAppointment()

        setLogoutButton()
        observeMessageResponse()

        Repository.apply {
            getAllDokter()
            getAllTreatment()
            //loggedUser.value?.id?.let { getAllPasienKunjunganHistory(it) }
        }

    }

    private fun observeMessageResponse(){
        Repository.message.observe(this){
            Toast.makeText(this@MainActivity, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    fun setLogoutButton(){
        binding?.btnLogout?.setOnClickListener {
            userPreferences.deleteSession()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }


    fun getHistoryUserData(){

        Repository.currentPasien.observe(this@MainActivity){
            Repository.getAllPasienKunjunganHistory(it.id_pasien!!)
        }

    }

    fun setNextAppointment(){
        getHistoryUserData()
        if (Repository.pasienHistory != null){
            Repository.pasienHistory.observe(this@MainActivity){
                val data = it.last()
                //val data = Repository.dataAppointment.value.last()
                binding?.cardAppointmentNext?.apply {
                    tvCaseName.text = data.treatment?.case_name
                    tvNamaDokter.text = data.dokter?.namaDokter
                }
                binding?.tvNextApDate?.text = data.date
            }
        }

//        binding?.fabAddAppointment?.setOnClickListener {
//            startNewProsesPendaftaran()
//        }
    }


    fun startNewProsesPendaftaran(){
        val intent = Intent(this@MainActivity, PendaftaranActivity::class.java)
        startActivity(intent)
    }

    fun setToHistory(){
        binding?.btnSeeAllAppointment?.setOnClickListener {
            val intent = Intent(this@MainActivity, HistoryActivity::class.java)
            startActivity(intent)
            //finish()
        }
    }

    override fun onResume() {

        setNextAppointment()
        super.onResume()
        //DummyData.dummyAppointmentList.add(Repository.dataAppointment.value!!)
    }


    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}