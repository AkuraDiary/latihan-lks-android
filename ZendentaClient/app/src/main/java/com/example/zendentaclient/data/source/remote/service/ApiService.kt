package com.example.zendentaclient.data.source.remote.service
import com.example.zendentaclient.data.source.remote.response.*
import com.example.zendentaclient.model.Treatment
import com.example.zendentaclient.model.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    //AUTH
    @POST("user/auth")
    fun authUser(
        @Body user : User
    ): Call<UserResponse>
//    AUTH


    //TREATMENTS
    @GET("treatments")
    fun getAllTreatments() : Call<List<Treatment>>

    @GET("treatments/{id}")
    fun getTreatmentsById(
        @Path("id") id : Int
    ) : Call<Treatment>

    //TREATMENTS


    //DOKTER
    @GET("dokter")
    fun getAllDokter() : Call<List<DokterResponse>>

    @GET("dokter/{id}")
    fun getDokterById(
        @Path("id") id : Int
    ) : Call<DokterResponse>

    //DOKTER


    //RECORD
    @GET("record")
    fun getAllRecord() : Call<List<RecordResponse>>

    @GET("record/{id}")
    fun getRecordById(
        @Path("id") id : Int
    ) : Call<RecordResponse>
    //RECORD


    //PASIEN
    @GET("pasien")
    fun getAllPasien() : Call<List<PasienResponse>>

    @GET("pasien/{id}")
    fun getPasienByIdUser(
        @Path("id") id : Int
    ) : Call<PasienResponse>
    //PASIEN

    //KUNJUNGAN
    @GET("kunjungan")
    fun getAllKunjungan() : Call<List<KunjunganResponse>>

    @GET("kunjungan/{id}")
    fun getKunjunganById(
        @Path("id") id : Int
    ) : Call<KunjunganResponse>

    @GET("pasien/{id}/kunjungan")
    fun getAllKunjunganOfPatient(
        @Path("id") id: Int
    ) : Call<List<KunjunganResponse>>
    //KUNJUNGAN

    //@FormUrlEncoded
    @POST("kunjungan")
    fun addNewKunjungan(
        @Body dataKunjungan : KunjunganRequest
    ):Call<KunjunganResponse>
}