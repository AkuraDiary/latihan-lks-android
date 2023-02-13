package com.example.zendentaclient.data.mapper

import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.data.source.remote.response.DokterResponse
import com.example.zendentaclient.data.source.remote.response.KunjunganResponse
import com.example.zendentaclient.data.source.remote.response.UserResponse
import com.example.zendentaclient.model.Appointment
import com.example.zendentaclient.model.Dokter
import com.example.zendentaclient.model.User

object DataMapper {
    fun MapUserResponseToUser(data: UserResponse?): User? {
        return User(
            id = data?.id_user,
            email = data?.email,
            username = data?.username,
            password = data?.password
        )

    }

    fun MapDokterResponseToModel(listData: List<DokterResponse>?): List<Dokter>? {
        var result : ArrayList<Dokter> = arrayListOf()

        if (listData != null) {
            for(itemDokter in listData){
                var dokter = Dokter(
                    itemDokter.id_dokter,
                    itemDokter.nama,
                    "",
                    "",
                    "",
                    null
                )

                result.add(dokter)
            }
        }
        return result
    }

    fun MapKunjunganResponseToAppointment(listData: List<KunjunganResponse>?): List<Appointment> {
        var result : ArrayList<Appointment> = arrayListOf()

        if (listData != null) {
            for(item in listData){
                var appointment = Appointment(
                    dokter = null,
                    date =item.tanggal_kunjungan?.substringBefore("T"),
                    treatment = null,
                    status = item.status_pembayaran
                )
                var tempTreatmentList = Repository.allTreatments.value
                appointment.treatment = tempTreatmentList?.find {
                    it.id_treatment == item.id_treatment
                }

                var tempDokterList = Repository.allDokter.value
                appointment.dokter = tempDokterList?.find {
                    it.idDokter == item.id_dokter
                }

                result.add(appointment)
            }
        }
        return result
    }
}