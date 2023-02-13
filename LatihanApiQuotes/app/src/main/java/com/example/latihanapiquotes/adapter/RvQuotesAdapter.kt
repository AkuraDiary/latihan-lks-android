package com.example.latihanapiquotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanapiquotes.data.model.Result
import com.example.latihanapiquotes.databinding.QuoteItemLayoutBinding

class RvQuotesAdapter : RecyclerView.Adapter<RvQuotesAdapter.QuotesViewHolder>() {

    private var listQuotes : ArrayList<Result> = arrayListOf()

    inner class QuotesViewHolder(val binding: QuoteItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(data : Result){
            binding.tvQuotes.text = data.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val itemBinding = QuoteItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuotesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val item = listQuotes[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return listQuotes.size
    }

    fun setData(listData : List<Result>){
        this.listQuotes.apply {
            clear()
            addAll(listData)
        }
        notifyDataSetChanged()
    }
}