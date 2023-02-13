package com.example.zendentaclient.data

import com.example.zendentaclient.model.Appointment
import com.example.zendentaclient.model.Dokter
import com.example.zendentaclient.model.Treatment

object DummyData {
    var daftarDokter: List<Dokter> = listOf(
        Dokter(
            1,
            "drg. Nunuk Setiyawan, Sp.BM",
            "Senin",
            "16.00",
            "19.00",
            100000
        ),

        Dokter(
            2,
            "drg. Hari Purbowisono",
            "Selasa",
            "16.00",
            "19.00",
            50000
        ),

        Dokter(
            3,
            "drg. Vita Indriyani",
            "Selasa",
            "16.00",
            "18.00",
            50000
        )
    )
    var listTreatment : List<Treatment> = listOf(
        Treatment(1, "cabut gigi"),
        Treatment(2, "consultation"),
        Treatment(3, "whitening"),
    )
    var dummyAppointmentList: ArrayList<Appointment> = arrayListOf()




}