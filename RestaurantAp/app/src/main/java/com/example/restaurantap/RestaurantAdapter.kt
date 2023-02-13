package com.example.restaurantap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantap.databinding.ItiemRestaurantLayoutBinding
import com.example.restaurantap.model.RestaurantModel

class RestaurantAdapter(private val listRestaurant:List<RestaurantModel>) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =ItiemRestaurantLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listRestaurant.size
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantItem = listRestaurant[position]
        holder.bind(restaurantItem)
//        Toast.makeText(, restaurantModel.name, Toast.LENGTH_LONG).Show()
    }

    inner class RestaurantViewHolder(private val binding: ItiemRestaurantLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(restaurantModel: RestaurantModel){
            binding.tvRestaurantName.text = restaurantModel.name
            binding.tvRestaurantAlamat.text = restaurantModel.address
            itemView.setOnClickListener {  Toast.makeText(itemView.context, restaurantModel.name, Toast.LENGTH_SHORT).show()}
        }
    }


}