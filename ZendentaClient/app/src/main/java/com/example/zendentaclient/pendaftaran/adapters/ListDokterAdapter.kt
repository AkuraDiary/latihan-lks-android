package com.example.zendentaclient.pendaftaran.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.databinding.ListDokterLayoutBinding
import com.example.zendentaclient.model.Dokter
import kotlin.collections.ArrayList

class ListDokterAdapter : RecyclerView.Adapter<ListDokterAdapter.ListDokteViewHolder>() {

    private var dokterList:ArrayList<Dokter> = arrayListOf()
    fun setData(data : List<Dokter>){
        dokterList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
    inner class ListDokteViewHolder(private val binding: ListDokterLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(dokterItem : Dokter){

            binding.apply {
                tvNamaDokter.text = dokterItem.namaDokter
                tvHariPraktek.text = dokterItem.hariPraktek
                tvJamPraktek.text =Repository.formatJamPraktek(dokterItem.jamMulaiPraktek.toString(),
                    dokterItem.jamAkhirPraktek.toString()
                )

                tvFeeDokter.text = dokterItem.biayaPerSesi?.let { Repository.formatIntToRp(it) }
                root.setOnClickListener {
                    Repository._dataSelectedDokter.value = dokterItem
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDokteViewHolder {
        val viewBinding =
            ListDokterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListDokteViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ListDokteViewHolder, position: Int) {
        val item = dokterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dokterList.size
    }
}