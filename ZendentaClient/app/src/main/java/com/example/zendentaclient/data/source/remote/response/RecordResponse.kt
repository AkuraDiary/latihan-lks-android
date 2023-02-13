package com.example.zendentaclient.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecordResponse (

        var id_record : Int?,
        var id_kunjungan : Int?,
        var status : String?
//{
//        "id_record": 1,
//        "id_kunjungan": 2,
//        "status": "sample string 3"
//},
        ) : Parcelable
