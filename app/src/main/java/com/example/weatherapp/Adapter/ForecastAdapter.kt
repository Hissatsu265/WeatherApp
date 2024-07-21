package com.example.weatherapp.Adapter

import android.icu.util.Calendar
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.weatherapp.databinding.ForecastViewholderBinding
import com.example.weatherapp.model.ForecastResponseApi
import java.text.SimpleDateFormat

class ForecastAdapter:RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {
    private lateinit var binding:ForecastViewholderBinding
    inner class ViewHolder:RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastAdapter.ViewHolder {
        val infate=LayoutInflater.from(parent.context)
        binding=ForecastViewholderBinding.inflate(infate,parent,false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ForecastAdapter.ViewHolder, position: Int) {
        val binding=ForecastViewholderBinding.bind(holder.itemView)
        val data=SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(differ.currentList[position].dtTxt.toString())
        val calendar=Calendar.getInstance()
        calendar.time=data

        val datOfweek=when(calendar.get(Calendar.DAY_OF_WEEK)){
            2->"Mon"
            3->"Tue"
            4->"Web"
            5->"Thu"
            6->"Fri"
            7->"Sat"
            1->"Sun"
            else -> {"..."}
        }
        binding.nameDay.text=datOfweek
        val hour=calendar.get(Calendar.HOUR_OF_DAY)
        val amPm= if(hour<12) "am" else "pm"
        val hour12=calendar.get(Calendar.HOUR)
        binding.hour.text=hour12.toString() + amPm

        binding.temp.text= differ.currentList[position].main?.temp.toString() + "°"

        val icon = when(differ.currentList[position].weather?.get(0)?.icon.toString()){
            "01d","0n"->"sunny"
            "02d","02n","03d","03n"->"cloudy_sunny"
            "04d","04n"-> "cloudy"
            "09d","09n"->"rainy"
            "10d","10n"->"rainy"
            "11d","11n"->"storm"
            "13d","13n"->"snowy"
            "50d","50n"->"windy"
            else -> {"..."}
        }
        val drawableResource:Int=binding.root.resources.getIdentifier(
            icon,"drawable",binding.root.context.packageName
        )
        Glide.with(binding.root.context)
            .load(drawableResource)
            .into(binding.pic)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
    private val differCallback=object :DiffUtil.ItemCallback<ForecastResponseApi.data1>(){
        override fun areItemsTheSame(
            oldItem: ForecastResponseApi.data1,
            newItem: ForecastResponseApi.data1
        ): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(
            oldItem: ForecastResponseApi.data1,
            newItem: ForecastResponseApi.data1
        ): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,differCallback)
}