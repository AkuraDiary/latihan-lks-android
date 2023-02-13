package com.example.zendentaclient.pendaftaran.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zendentaclient.data.DummyData
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.databinding.FragmentPilihDokterBinding
import com.example.zendentaclient.pendaftaran.adapters.ListDokterAdapter

class PilihDokterFragment : Fragment() {

    private var binding : FragmentPilihDokterBinding? = null
    private var rvDokterAdapter:  ListDokterAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvDokterAdapter = ListDokterAdapter()
        binding = FragmentPilihDokterBinding.inflate(layoutInflater)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //set data for rv
        Repository.allDokter.observe(viewLifecycleOwner){
            rvDokterAdapter?.setData(it)
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvDokter()

        observeSelectedDokter()
    }


    fun setupRvDokter(){
        binding?.rvDaftarDokter?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvDokterAdapter
        }
    }



    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    fun observeSelectedDokter(){

        Repository.dataSelectedDokter.observe(viewLifecycleOwner){
            binding?.apply {

                tvNamaDokter.text = it.namaDokter
                tvHariPraktek.text = it.hariPraktek
                tvJamPraktek.text = Repository.formatJamPraktek(it.jamMulaiPraktek.toString(),
                    it.jamAkhirPraktek.toString()
                )
                tvFeeDokter.text = it.biayaPerSesi?.let { it1 -> Repository.formatIntToRp(it1) }
            }
        }
    }

    companion object {
       fun getInstance() = PilihDokterFragment()
    }
}