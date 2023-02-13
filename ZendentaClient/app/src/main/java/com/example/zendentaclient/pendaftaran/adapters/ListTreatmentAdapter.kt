package com.example.zendentaclient.pendaftaran.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zendentaclient.data.Repository
import com.example.zendentaclient.databinding.ListTreatmentLayoutBinding
import com.example.zendentaclient.model.Treatment
import kotlin.collections.ArrayList

class ListTreatmentAdapter : RecyclerView.Adapter<ListTreatmentAdapter.ListTreatmentViewHolder>() {

    private var dokterList:ArrayList<Treatment> = arrayListOf()
    fun setData(data : List<Treatment>){
        dokterList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
    inner class ListTreatmentViewHolder(private val binding: ListTreatmentLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(treatmentItem : Treatment){

            binding.apply {
                tvNamaTreatment.text = treatmentItem.case_name
               // tvHargaTreatment.text = Repository.formatIntToRp(treatmentItem.harga ?: 0)
                root.setOnClickListener {
                    Repository._dataSelectedTreatment.value = treatmentItem
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListTreatmentViewHolder {
        val viewBinding =
            ListTreatmentLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListTreatmentViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: ListTreatmentViewHolder, position: Int) {
        val item = dokterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dokterList.size
    }
}