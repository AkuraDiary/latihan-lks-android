package com.example.pildunandroid.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pildunandroid.adapter.HasilAdapter
import com.example.pildunandroid.data.DataMapper
import com.example.pildunandroid.data.Repository

import com.example.pildunandroid.databinding.FragmentHasilBinding
import com.example.pildunandroid.model.binding.JadwalBindingModel
import com.example.pildunandroid.model.response.HasilModel
import com.example.pildunandroid.model.response.JadwalModel
import com.example.pildunandroid.model.response.TimModel

class HasilFragment : Fragment() {
    private var binding : FragmentHasilBinding? = null

    var hasilAdapter = HasilAdapter()
    val repository = Repository

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = FragmentHasilBinding.inflate(layoutInflater)
    }

    fun refreshRV(){
        Repository.listJadwalBinding.observe(viewLifecycleOwner){
            listJadwalBinding = it
        }
        //listJadwalBinding = //DataMapper.MapResponseTimAndJadwal(listJadwal, listTim)
        hasilAdapter.setData(
            DataMapper.MapResponseJadwalAndHasil(listHasil, listJadwalBinding)
        )
    }
    fun setHasilRv(){
        binding?.rvMainHasil?.apply {
            adapter = hasilAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        hasilAdapter.setData(
            DataMapper.MapResponseJadwalAndHasil(listHasil, listJadwalBinding)
        )
        binding?.progressBar?.visibility = View.GONE
    }

    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fetchHasilData()
        setHasilRv()
        return binding?.root//super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        repository.message.observe(viewLifecycleOwner){
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        if (listJadwalBinding.isNotEmpty()){
            binding?.progressBar?.visibility = View.GONE
        }
    }

    fun fetchHasilData(){
        repository.apply {
            listJadwalResponse.observe(viewLifecycleOwner){
                listJadwal = it
                refreshRV() // refresh the rv immediately
            }
            listTimResponse.observe(viewLifecycleOwner){
                listTim = it
                refreshRV() // refresh the rv immediately
            }
            listHasilResponse.observe(viewLifecycleOwner){
                listHasil = it
                refreshRV() // refresh the rv immediately
            }

        }

    }


    companion object{
        fun getIntance() = HasilFragment()
        private var listJadwal : List<JadwalModel> = listOf()
        private var listTim : List<TimModel> = listOf()
        var listHasil : List<HasilModel> = listOf()
        private var listJadwalBinding : List<JadwalBindingModel> = listOf()
    }
}