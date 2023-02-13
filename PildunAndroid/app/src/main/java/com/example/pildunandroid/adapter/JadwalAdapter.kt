package com.example.pildunandroid.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pildunandroid.DetailActivity
import com.example.pildunandroid.R
import com.example.pildunandroid.databinding.ItemJadwalLayoutBinding
import com.example.pildunandroid.model.binding.JadwalBindingModel
import com.example.pildunandroid.model.response.JadwalModel

class JadwalAdapter : RecyclerView.Adapter<JadwalAdapter.JadwalViewHolder>() {
    var listData : ArrayList<JadwalBindingModel> = arrayListOf()
    private lateinit var context: Context

    inner class JadwalViewHolder(var binding : ItemJadwalLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : JadwalBindingModel){
            //BIND DATA, AND CLICK HERE
            binding.tvNamaLiga.text = data.nama_liga
            binding.tvTanggalMain.text = data.tanggal_main.toString( )
            binding.tvNamaTimSatu.text = data.tim_satu?.nama_tim
            binding.tvNamaTimDua.text = data.tim_dua?.nama_tim

            bindImageNegara(data.tim_satu!!.img_url.toString(), binding.imgViewTimSatu)
            bindImageNegara(data.tim_dua!!.img_url.toString(), binding.imgViewTimDua)

            binding.root?.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("dataJadwal", data)
                context.startActivity(intent)
                //Toast.makeText(context, data.nama_liga, Toast.LENGTH_SHORT).show()
            }//
//            //GLIDE

        }

        private fun bindImageNegara(url :String, targetView : ImageView){
            Glide.with(context)
                .load(url)
                .placeholder(R.drawable.balbalan)
                .into(targetView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JadwalViewHolder {
        context = parent.context
        var layoutBinding = ItemJadwalLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JadwalViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: JadwalViewHolder, position: Int) {
        val item = listData[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setData(data: List<JadwalBindingModel>){
        Log.d("Jadwal Adapter Set Data", data.toString())
        listData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}