package com.example.zendentaclient.pendaftaran.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.databinding.FragmentPendaftaranSummarryBinding

class PendaftaranSummarryFragment : Fragment() {

    private var binding : FragmentPendaftaranSummarryBinding? = null
    //private var appointment : Appointment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentPendaftaranSummarryBinding.inflate(layoutInflater)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Repository.dataAppointment.observe(viewLifecycleOwner){
//            appointment = it
//        }
        bindData()
    }


    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    private fun bindData(){
        Repository.dataAppointment.observe(viewLifecycleOwner){
            binding?.apply {
                tvNamaDokter.text = "Dokter : ${it?.dokter?.namaDokter} "
//                tvHariPraktek.text = "Hari :  ${it?.dokter?.hariPraktek} "
//                tvJamPraktek.text = "Jam : ${ Repository.formatJamPraktek(
//                    it?.dokter?.jamMulaiPraktek!!,
//                    it?.dokter?.jamAkhirPraktek!!
//                )}"

                tvNamaTreatment.text = "Treatment : ${it?.treatment?.case_name}"
                tvTanggal.text = "Tanggal : ${it.date}"

                tvHarga.text = "Total :  ${Repository.formatIntToRp(it?.treatment?.harga!!)}"
            }
        }
    }
    companion object {
        fun getInstance() = PendaftaranSummarryFragment()
    }
}