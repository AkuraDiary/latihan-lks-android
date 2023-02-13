package com.example.zendentaclient.pendaftaran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN
import com.example.zendentaclient.R
import com.example.zendentaclient.data.DummyData
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.data.source.remote.response.KunjunganRequest
import com.example.zendentaclient.databinding.ActivityPendaftaranBinding
import com.example.zendentaclient.model.Appointment
import com.example.zendentaclient.pendaftaran.fragments.PIlihWaktuKunjunganFragment
import com.example.zendentaclient.pendaftaran.fragments.PendaftaranSummarryFragment
import com.example.zendentaclient.pendaftaran.fragments.PilihDokterFragment
import com.example.zendentaclient.pendaftaran.fragments.PilihTreatmentFragment
import com.example.zendentaclient.riwayat.HistoryAdapter

class PendaftaranActivity : AppCompatActivity() {
    private var binding: ActivityPendaftaranBinding? = null

    private var fragmentPilihDokter = PilihDokterFragment.getInstance()
    private var fragmentPilihJadwal = PIlihWaktuKunjunganFragment.getInstance()
    private var fragmentPilihTreatment = PilihTreatmentFragment.getInstance()
    private var fragmentSummarry = PendaftaranSummarryFragment.getInstance()

    private var _progress: Int = 0
    //private var
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPendaftaranBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        goToPilihDokter()

        setupNextButton()
        observeResult()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    private fun observeResult(){
        Repository.dataSelectedDokter.observe(this){
            Repository.dataAppointment.value?.dokter =it
        }

        Repository.dataSelectedTreatment.observe(this){
            Repository.dataAppointment.value?.treatment =it
        }

        Repository.selectedDate.observe(this){
            Repository.dataAppointment.value?.date =it
        }
    }

    private fun setupNextButton(){

        binding?.btnBtnAddPendaftaran?.setOnClickListener{

            when{

                supportFragmentManager.fragments.contains(fragmentPilihJadwal) -> {
                    binding?.btnBtnAddPendaftaran?.text = "Next"

                    val selectedDate = fragmentPilihJadwal.getSelectedDate()
                    Repository._selectedDate.value = Repository.formatDateToString(selectedDate)

                    if (Repository.selectedDate.value != null){
                        val appointment = Appointment(
                            Repository.dataSelectedDokter.value,
                            Repository.selectedDate.value,
                            Repository.dataSelectedTreatment.value
                        )
                        Repository._dataAppointment.value = appointment

                        goToSummary()
                    }else{
                        Toast.makeText(this@PendaftaranActivity, "Pilih Jadwal Dahulu", Toast.LENGTH_SHORT).show()
                    }
                }

                supportFragmentManager.fragments.contains(fragmentPilihTreatment) -> {
                    binding?.btnBtnAddPendaftaran?.text = "Next"

                    if (Repository.dataSelectedTreatment.value != null){
                            goToPilihJadwal()
                        }else{
                            Toast.makeText(this@PendaftaranActivity, "Pilih Treatment Dahulu", Toast.LENGTH_SHORT).show()
                        }
                }
                supportFragmentManager.fragments.contains(fragmentPilihDokter) -> {
                    binding?.btnBtnAddPendaftaran?.text = "Next"

                    if (Repository.dataSelectedDokter.value != null){
                            goToPilihTreatment()
                        }else{
                            Toast.makeText(this@PendaftaranActivity, "Pilih Dokter Dahulu", Toast.LENGTH_SHORT).show()
                        }
                }

                supportFragmentManager.fragments.contains(fragmentSummarry) -> {
                    binding?.btnBtnAddPendaftaran?.text = "Konfirmasi"


                    val data = KunjunganRequest(
                        id_pasien = Repository.currentPasien.value?.id_pasien,
                        id_dokter = Repository.dataSelectedDokter.value?.idDokter,
                        id_treatment = Repository.dataSelectedTreatment.value?.id_treatment,
                        tanggal_kunjungan = Repository.selectedDate.value.toString(),
                        note = "-",
                        status_pembayaran = "request"
                    )

                    Repository.addNewKunjungan(data)

                    Repository.message.observe(this@PendaftaranActivity){
                        Toast.makeText(applicationContext,"Berhasil Menambah Kunjungan", Toast.LENGTH_SHORT).show()
                    }
                    //DummyData.dummyAppointmentList.add(Repository.dataAppointment.value!!)
                    //HistoryAdapter.getInstance().setData(DummyData.dummyAppointmentList)
//                    //TODO NOTIFY THE ADAPTER
//
//                    Log.d("Fragment Pendaftaran Summary", Repository.dataAppointment.value!!.toString())
//                    Log.d("Fragment Pendaftaran Summary", DummyData.dummyAppointmentList.toString())

                    finish()
                }
            }

        }
    }

    override fun onBackPressed() {
        _progress -= 25
        binding?.progressBar?.progress = _progress//?.minus(25)
        binding?.btnBtnAddPendaftaran?.text = "Next"
        if(supportFragmentManager.fragments.contains(fragmentPilihDokter)){
                 supportFragmentManager.popBackStack()
            finish()
        }
       super.onBackPressed()

    }


    private fun goToPilihDokter(){
        _progress +=25
        binding?.progressBar?.progress = _progress //.//plus(25)
        binding?.btnBtnAddPendaftaran?.text = "Next"
        changeFragment(fragmentPilihDokter)

    }
    private fun goToPilihTreatment(){
        _progress +=25
        binding?.progressBar?.progress = _progress//?.plus(25)
        binding?.btnBtnAddPendaftaran?.text = "Next"
        changeFragment(fragmentPilihTreatment)

    }
    private fun goToPilihJadwal(){
        _progress +=25
        binding?.progressBar?.progress = _progress//?.plus(25)
        binding?.btnBtnAddPendaftaran?.text = "Next"
        changeFragment(fragmentPilihJadwal)

    }

    private fun goToSummary(){
        _progress +=25
        binding?.progressBar?.progress = _progress//?.plus(25)
        binding?.btnBtnAddPendaftaran?.text = "Konfirmasi"
        changeFragment(fragmentSummarry)
    }


    fun changeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_CLOSE)
            .replace(binding?.pendaftaranActivityFragmentContainer?.id!!, fragment)
            .addToBackStack(null)
            .setTransition(TRANSIT_FRAGMENT_OPEN)
            .commit()

    }
}