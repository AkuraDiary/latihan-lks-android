package com.example.pildunandroid.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pildunandroid.DetailActivity
import com.example.pildunandroid.R
import com.example.pildunandroid.databinding.ItemHasilLayoutBinding
import com.example.pildunandroid.databinding.ItemJadwalLayoutBinding
import com.example.pildunandroid.model.binding.HasilBindingModel


class HasilAdapter() : RecyclerView.Adapter<HasilAdapter.HasilViewHolder>() {
    var listData : ArrayList<HasilBindingModel> = arrayListOf()
    private lateinit var context: Context

    inner class HasilViewHolder(var binding : ItemHasilLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : HasilBindingModel){
            //TODO BIND DATA, IMAGE AND CLICK HERE
            binding.tvNamaLiga.text = data.jadwal?.nama_liga
            binding.tvSkorTimSatu.text = data.skor_satu.toString()
            binding.tvSkorTimDua.text = data.skor_dua.toString()
            binding.tvTanggalMain.text = data.jadwal?.tanggal_main.toString()

            binding.tvNamaTimSatu.text = data.jadwal?.tim_satu?.nama_tim
            binding.tvNamaTimDua.text = data.jadwal?.tim_dua?.nama_tim

            bindImage(data.jadwal?.tim_satu?.img_url, binding?.imgViewTimSatu!!)
            bindImage(data.jadwal?.tim_dua?.img_url, binding?.imgViewTimDua!!)

            bindImage(data.header_url, binding?.itemHasilHeaderImg!!)
            binding?.root?.setOnClickListener {
                //Toast.makeText(context, data.jadwal?.nama_liga, Toast.LENGTH_SHORT).show()

                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("dataHasil", data)
                context.startActivity(intent)

            }
        }

        fun bindImage(imageUrl : String?, targetView : ImageView){
            Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.thumbnail)
                .into(targetView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HasilViewHolder {
        context = parent.context
        var layoutBinding = ItemHasilLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HasilViewHolder(layoutBinding)
    }

    override fun onBindViewHolder(holder: HasilViewHolder, position: Int) {
        val item = listData[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun setData(data : List<HasilBindingModel>){
        listData.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
}