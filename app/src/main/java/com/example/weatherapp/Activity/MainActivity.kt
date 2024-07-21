package com.example.weatherapp.Activity

import android.content.Intent
import android.graphics.Color
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModel
import com.example.weatherapp.R
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.databinding.ActivityMainBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapter.ForecastAdapter
import com.example.weatherapp.model.CurrentResponseApi
import com.example.weatherapp.model.ForecastResponseApi
import com.github.matteobattilana.weather.PrecipType
import eightbitlab.com.blurview.RenderScriptBlur
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import javax.security.auth.callback.CallbackHandler

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val weatherViewModel:WeatherViewModel by viewModels()
    private val calendar by lazy { Calendar.getInstance()}
    private val forecastAdapter by lazy{ ForecastAdapter()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor= Color.TRANSPARENT
        }
        binding.apply {
            var lat=intent.getDoubleExtra("lat",0.0)
            var lon=intent.getDoubleExtra("lng",0.0)
            var name=intent.getStringExtra("name")
            if(lat==0.0 || lon ==0.0 )
            {
                lat=51.5
                lon=-0.12
                name="London"
            }
            addCity.setOnClickListener {
                startActivity(Intent(this@MainActivity,CityListActivity::class.java))
            }

            cityTxt.text=name
            progressBar.visibility= View.VISIBLE
            // current Temp
            weatherViewModel.loadCurrentWeather(lat,lon,"metric")
                .enqueue(object: retrofit2.Callback<CurrentResponseApi>{
                    override fun onResponse(
                        call: Call<CurrentResponseApi>,
                        response: Response<CurrentResponseApi>
                    ) {
                        if(response.isSuccessful){
                            val data=response.body()
                            progressBar.visibility=View.GONE
                            detailLayout.visibility=View.VISIBLE
                            data?.let{
                                statustxt.text=it.weather?.get(0)?.main ?: "no data"
                                windTxt.text = it.wind?.speed?.let { speed ->
                                    Math.round(speed).toString()} ?: "0"+"Km/h"
                                humidityTxt.text= it.main?.humidity.toString()  + "%"

                                currentTemp.text=it.main?.temp?.let{
                                    Math.round(it).toString()
                                }?:"0"+"°"
                                maxTempTxt.text=it.main?.tempMax?.let{
                                    Math.round(it).toString()
                                }?:"0"+"°"
                                minTempTxt.text=it.main?.tempMin?.let{
                                    Math.round(it).toString()
                                }?:"0"+"°"

                                val drawable=if(isNight()) R.drawable.night_bg
                                            else{
                                                 it.weather?.get(0)?.icon?.let { it1 -> setDynamicWeather(it1)}
                                            }
                                if (drawable != null) {
                                    mainview.setImageResource(drawable)
                                    setEffectWeather(it.weather?.get(0)?.icon?:"")
                                }
                            }
                        }
                    }

                    override fun onFailure(call: Call<CurrentResponseApi>, t: Throwable) {
                        Toast.makeText(this@MainActivity, "Fail", Toast.LENGTH_SHORT).show()
                    }

                })
            // Blur view
            var radius=10f
            val davorView=window.decorView
            val rootView=(davorView.findViewById<ViewGroup?>(com.google.android.material.R.id.content))
            val windowBackground=davorView.background
            rootView?.let {
                Blurview.setupWith(it,RenderScriptBlur(this@MainActivity))
                    .setFrameClearDrawable(windowBackground)
                    .setBlurRadius(radius)
                Blurview.outlineProvider= ViewOutlineProvider.BACKGROUND
                Blurview.clipToOutline=true
            }
            // Forecast weather
            weatherViewModel.loadForecastWeather(lat,lon,"metric").enqueue(object :retrofit2.Callback<ForecastResponseApi>{
                override fun onResponse(
                    call: Call<ForecastResponseApi>,
                    response: Response<ForecastResponseApi>
                ) {
                    if(response.isSuccessful){
                        val data=response.body()
                        Blurview.visibility=View.VISIBLE
                        data?.let{
                            forecastAdapter.differ.submitList(it.list)
                            forecastRv.apply {
                                layoutManager=LinearLayoutManager(
                                    this@MainActivity,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                                adapter=forecastAdapter

                            }
                        }

                    }
                }

                override fun onFailure(call: Call<ForecastResponseApi>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failure to load forecast weather", Toast.LENGTH_SHORT).show()
                }

            })
                


        }

    }
    private  fun isNight():Boolean{
        return calendar.get(Calendar.HOUR_OF_DAY)>=18
                && calendar.get(Calendar.HOUR_OF_DAY)<3
    }
    private fun initWeatherView(type:PrecipType){
        binding.weatherView.apply {
            setWeatherData(type)
            angle=-20
            emissionRate=100.0f
        }
    }

    private fun setDynamicWeather(icon:String):Int{
        return when (icon.dropLast(1)){
            "01"->{
                initWeatherView(PrecipType.CLEAR)
                R.drawable.snow_bg
            }
            "02","03","04"->{
                initWeatherView(PrecipType.CLEAR)
                R.drawable.cloudy_bg
            }
            "09","10","11"->{
                initWeatherView(PrecipType.RAIN)
                R.drawable.rainy_bg
            }
            "50"->{
                initWeatherView(PrecipType.SNOW)
                R.drawable.haze_bg
            }
            "13"->{
                initWeatherView(PrecipType.SNOW)
                R.drawable.snow_bg
            }
            else -> {
                initWeatherView(PrecipType.CLEAR)
                0
            }
        }

    }
    private fun setEffectWeather(icon:String) {
       when (icon.dropLast(1)){
            "01"->{
                initWeatherView(PrecipType.CLEAR)
            }
            "02","03","04"->{
                initWeatherView(PrecipType.CLEAR)
            }
            "09","10","11"->{
                initWeatherView(PrecipType.RAIN)
            }
            "50","13"->{
                initWeatherView(PrecipType.SNOW)
            }
            else -> {initWeatherView(PrecipType.CLEAR)}
        }

    }
}