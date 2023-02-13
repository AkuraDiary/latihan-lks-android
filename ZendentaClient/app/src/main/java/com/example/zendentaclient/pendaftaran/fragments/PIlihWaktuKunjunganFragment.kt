package com.example.zendentaclient.pendaftaran.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.zendentaclient.databinding.FragmentPilihWaktuKunjunganBinding

import java.util.Calendar
import java.util.Date

class PIlihWaktuKunjunganFragment : Fragment() {

    private var binding : FragmentPilihWaktuKunjunganBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentPilihWaktuKunjunganBinding.inflate(layoutInflater)

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
    }

    fun  getSelectedDate() : Date{
        val calendarr = Calendar.getInstance()
        val datePicker = binding?.datePicker

        calendarr.set(datePicker!!.year,datePicker!!.month,datePicker!!.dayOfMonth  )

        return calendarr.time
    }


    override fun onDetach() {
        super.onDetach()
        binding = null
    }

    companion object {
        fun getInstance() = PIlihWaktuKunjunganFragment()
    }
}