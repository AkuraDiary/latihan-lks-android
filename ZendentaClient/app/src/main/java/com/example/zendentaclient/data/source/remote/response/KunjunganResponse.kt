package com.example.zendentaclient.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class KunjunganResponse(
    var id_dokter: Int?,
    var id_kunjungan: Int?,
    //var id_nurse: Any?,
    var id_pasien: Int?,
    var id_treatment: Int?,
    var note: String?,
    var status_pembayaran: String?,
    var tanggal_kunjungan: String?
):Parcelable



@Parcelize
data class KunjunganRequest(
    var id_pasien: Int?,
    var id_dokter: Int?,
    var id_treatment: Int?,
    var note: String?,
    var tanggal_kunjungan: String?,
    var status_pembayaran: String?,
):Parcelable