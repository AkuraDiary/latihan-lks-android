package com.example.zendentaclient.pendaftaran.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.zendentaclient.data.DummyData
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.databinding.FragmentPilihTreatmentBinding
import com.example.zendentaclient.pendaftaran.adapters.ListTreatmentAdapter

class PilihTreatmentFragment : Fragment() {

    private var binding : FragmentPilihTreatmentBinding? = null
    private var rvTreatmentAdapter:  ListTreatmentAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        rvTreatmentAdapter = ListTreatmentAdapter()
        binding = FragmentPilihTreatmentBinding.inflate(layoutInflater)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //set the data for rv
        Repository.allTreatments.observe(viewLifecycleOwner){
            rvTreatmentAdapter?.setData(it)
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRvTreatment()

        observeSelectedTreatment()
    }

    fun setupRvTreatment(){
        binding?.rvDaftarDokter?.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = rvTreatmentAdapter
        }
    }



    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    fun observeSelectedTreatment(){

        Repository.dataSelectedTreatment.observe(viewLifecycleOwner){
            binding?.apply {
                tvNamaTreatment.text = it.case_name
                tvHargaTreatment.text = Repository.formatIntToRp(it.harga!!)
            }
        }
    }

    companion object {
       fun getInstance() = PilihTreatmentFragment()
    }
}