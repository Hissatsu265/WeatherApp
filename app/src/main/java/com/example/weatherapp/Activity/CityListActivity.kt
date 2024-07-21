package com.example.weatherapp.Activity

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weatherapp.Adapter.CityAdapter
import com.example.weatherapp.R
import com.example.weatherapp.ViewModel.CityViewModel
import com.example.weatherapp.databinding.ActivityCityListBinding
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.model.CityResponseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CityListActivity : AppCompatActivity() {
    lateinit var binding:ActivityCityListBinding

    private val cityAdapter by lazy {CityAdapter()}
    private val cityViewModel:CityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityCityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            statusBarColor=Color.TRANSPARENT
        }

        binding.apply {
            cityeditText.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    progressBar.visibility= View.VISIBLE
                    cityViewModel.loadCity(s.toString(),10).enqueue(object :Callback<CityResponseApi>{
                        override fun onResponse(
                            call: Call<CityResponseApi>,
                            response: Response<CityResponseApi>
                        ) {
                            if(response.isSuccessful){
                                val data=response.body()
                                data?.let {
                                    progressBar.visibility=View.GONE
                                    cityAdapter.differ.submitList(it)
                                    cityRv.apply {
                                        layoutManager=LinearLayoutManager(
                                            this@CityListActivity,
                                            LinearLayoutManager.HORIZONTAL,
                                            false
                                        )
                                        adapter=cityAdapter
                                    }
                                }
                            }
                        }

                        override fun onFailure(call: Call<CityResponseApi>, t: Throwable) {
                            Toast.makeText(this@CityListActivity, "Fail", Toast.LENGTH_SHORT).show()
                        }

                    })
                }

            })
        }
    }
}