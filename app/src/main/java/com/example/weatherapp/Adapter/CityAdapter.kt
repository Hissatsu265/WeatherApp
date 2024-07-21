package com.example.weatherapp.Adapter

import android.content.Intent
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.Activity.MainActivity
import com.example.weatherapp.databinding.CityViewholderBinding
import com.example.weatherapp.databinding.ForecastViewholderBinding
import com.example.weatherapp.model.City
import com.example.weatherapp.model.CityResponseApi
import com.example.weatherapp.model.CityResponseApiItem
import com.example.weatherapp.model.ForecastResponseApi
import java.text.SimpleDateFormat

class CityAdapter : RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    private lateinit var binding: CityViewholderBinding
    inner class ViewHolder: RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityAdapter.ViewHolder {
        val infate= LayoutInflater.from(parent.context)
        binding= CityViewholderBinding.inflate(infate,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: CityAdapter.ViewHolder, position: Int) {

        val binding= CityViewholderBinding.bind(holder.itemView)
        binding.cityname.text=differ.currentList[position].name
        binding.root.setOnClickListener {
            val intent= Intent(binding.root.context,MainActivity::class.java)
            intent.putExtra("lat",differ.currentList[position].lat)
            intent.putExtra("lng",differ.currentList[position].lon)
            intent.putExtra("name",differ.currentList[position].name)
            binding.root.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private val differCallback=object :DiffUtil.ItemCallback<CityResponseApiItem>(){
        override fun areItemsTheSame(
            oldItem: CityResponseApiItem,
            newItem: CityResponseApiItem
        ): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: CityResponseApiItem,
            newItem: CityResponseApiItem
        ): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,differCallback)

}