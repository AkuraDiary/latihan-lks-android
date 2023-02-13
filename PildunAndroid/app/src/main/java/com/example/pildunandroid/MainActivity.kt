package com.example.pildunandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.*
import com.example.pildunandroid.data.Repository
import com.example.pildunandroid.databinding.ActivityMainBinding
import com.example.pildunandroid.fragment.HasilFragment
import com.example.pildunandroid.fragment.JadwalFragment


class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private var repository = Repository

    private val jadwalFragment = JadwalFragment.getIntance()
    private val hasilFragment = HasilFragment.getIntance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        requestData()

        setContentView(binding?.root)
        setUpNavigation()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    fun setUpNavigation() {
        binding?.apply {
            bottomNavigation?.setOnItemSelectedListener { menu ->
                when (menu.itemId) {
                    R.id.nav_jadwal -> {
                        changeFragment(jadwalFragment)
//                    binding?.tvJudul?.text = "Jadwal Piala Dunia"
                        return@setOnItemSelectedListener true
                    }
                    R.id.nav_hasil -> {
                        changeFragment(hasilFragment)
//                    binding?.tvJudul?.text = "Hasil Piala Dunia"
                        return@setOnItemSelectedListener true
                    }
                }
                false
            }
            changeFragment(jadwalFragment)
        }
    }

    fun  requestData(){
        repository.apply { //get the data first from api
            Log.d("Main Activity", "requesting the data")
            getAllJadwal()
            getAllTim()
            getAllHasil()
        }
    }


    fun changeFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_CLOSE)
            .setTransition(TRANSIT_FRAGMENT_OPEN)
            .replace(R.id.homeRootView, fragment)
            .commit()
    }
}