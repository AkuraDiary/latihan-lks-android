package com.example.pildunandroid.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pildunandroid.adapter.JadwalAdapter
import com.example.pildunandroid.data.DataMapper
import com.example.pildunandroid.data.Repository
import com.example.pildunandroid.databinding.FragmentJadwalBinding
import com.example.pildunandroid.model.response.JadwalModel
import com.example.pildunandroid.model.response.TimModel

class JadwalFragment : Fragment() {

    private var binding: FragmentJadwalBinding? = null

    var jadwalAdapter = JadwalAdapter()

    val repository = Repository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentJadwalBinding.inflate(layoutInflater)

    }


    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fetchDataJadwal() // call the data

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        setJadwalRv()//set up the rv
    }

    fun fetchDataJadwal() {

        repository.listJadwalResponse.observe(viewLifecycleOwner) {
            listJadwal = it
            Log.d("Jadwal Fragment", listJadwal.toString())
            setJadwalRv() //update the rv immediately
        }

        repository.listTimResponse.observe(viewLifecycleOwner) {
            listTim = it
            Log.d("Jadwal Fragment", listTim.toString())
            setJadwalRv() //update the rv immediately
        }

    }


    fun setJadwalRv() {

        jadwalAdapter.apply {
            val listJadwalBinding =
                DataMapper.MapResponseTimAndJadwal(listJadwal, listTim)

            Repository.listJadwalBinding.value = listJadwalBinding

            if (listJadwalBinding.isNotEmpty()) {
                binding?.progressBar?.visibility = View.GONE //hide the loading progress bar
            }
            setData(
                listJadwalBinding // insert data into rv
            )
        }
        binding?.rvMainJadwal?.apply {
            adapter = jadwalAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    companion object {
        fun getIntance() = JadwalFragment()
        private var listJadwal: List<JadwalModel> = listOf()
        private var listTim: List<TimModel> = listOf()
    }

}