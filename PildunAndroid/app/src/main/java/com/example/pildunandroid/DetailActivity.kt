package com.example.pildunandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.pildunandroid.data.Repository
import com.example.pildunandroid.databinding.ActivityDetailBinding
import com.example.pildunandroid.fragment.HasilFragment
import com.example.pildunandroid.model.binding.HasilBindingModel
import com.example.pildunandroid.model.binding.JadwalBindingModel

class DetailActivity : AppCompatActivity() {
    private var binding: ActivityDetailBinding? = null
    private val repository = Repository
    private var dataJadwal: JadwalBindingModel? = null
    private var dataHasil: HasilBindingModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        dataJadwal = intent.getParcelableExtra("dataJadwal")
        dataHasil = intent.getParcelableExtra("dataHasil")

        bindData()

    }

    fun bindData() {
        when {
            dataJadwal != null -> {
                binding?.apply {
                    tvNamaLiga.text = dataJadwal!!.nama_liga//jadwal?.nama_liga
                    tvTanggalMain.text = dataJadwal!!.tanggal_main//jadwal?.tanggal_main
                    tvNamaTimDua.text = dataJadwal!!.tim_satu?.nama_tim//jadwal!!.tim_dua!!.nama_tim
                    tvNamaTimSatu.text = dataJadwal!!.tim_dua?.nama_tim//jadwal!!.tim_satu!!.nama_tim

                    Glide.with(applicationContext).load(dataJadwal!!.tim_satu?.img_url).into(imgViewTimSatu)
                    Glide.with(applicationContext).load(dataJadwal!!.tim_satu?.img_url).into(imgViewTimDua)

                    val datahasil = HasilFragment.listHasil.find {
                        it.id_jadwal == dataJadwal!!.id_jadwal
                    }
                    if (dataJadwal != null){
                        tvSkorTimSatu.text = datahasil?.skor_satu.toString()
                        tvSkorTimDua.text = datahasil?.skor_dua.toString()

                        Glide.with(applicationContext).load(datahasil?.header_url).into(headerImage)
                        Glide.with(applicationContext).load(dataJadwal?.tim_satu?.img_url).into(imgViewSkorTimSatu)
                        Glide.with(applicationContext).load(dataJadwal?.tim_satu?.img_url).into(imgViewSkorTimDua)

                    }else{
                        binding!!.dataCardViewAtas.visibility = View.GONE
                    }
                }
            }
            dataHasil != null -> {
                binding?.apply {
                    tvNamaLiga.text = dataHasil!!.jadwal?.nama_liga
                    tvTanggalMain.text = dataHasil!!.jadwal?.tanggal_main
                    tvNamaTimDua.text = dataHasil!!.jadwal!!.tim_dua!!.nama_tim
                    tvNamaTimSatu.text = dataHasil!!.jadwal!!.tim_satu!!.nama_tim

                    tvSkorTimSatu.text = dataHasil!!.skor_satu.toString()
                    tvSkorTimDua.text = dataHasil!!.skor_dua.toString()

                    Glide.with(applicationContext).load(dataHasil!!.header_url).into(headerImage)

                    Glide.with(applicationContext).load(dataHasil!!.jadwal?.tim_satu?.img_url).into(imgViewTimSatu )
                    Glide.with(applicationContext).load(dataHasil!!.jadwal?.tim_satu?.img_url).into(imgViewSkorTimSatu)

                    Glide.with(applicationContext).load(dataHasil!!.jadwal?.tim_dua?.img_url).into(imgViewTimDua )
                    Glide.with(applicationContext).load(dataHasil!!.jadwal?.tim_dua?.img_url).into(imgViewSkorTimDua)
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}