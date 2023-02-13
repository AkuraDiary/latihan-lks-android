package com.example.zendentaclient.riwayat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.zendentaclient.databinding.HistoryLayoutBinding
import com.example.zendentaclient.model.Appointment

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private var historyList:ArrayList<Appointment> = arrayListOf()
    fun setData(data : List<Appointment>){
        historyList.apply {
            clear()
            addAll(data)
        }
        notifyDataSetChanged()
    }
    inner class HistoryViewHolder(private val binding: HistoryLayoutBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(appointmentItem : Appointment){

            binding.apply {
                tvCaseName.text = appointmentItem.treatment?.case_name
                tvNamaDokter.text = appointmentItem.dokter?.namaDokter
                tvDate.text = appointmentItem.date
                root.setOnClickListener {
                    //TODO set on click event here
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val viewBinding =HistoryLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = historyList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    companion object{
        fun getInstance() = HistoryAdapter()
    }
}