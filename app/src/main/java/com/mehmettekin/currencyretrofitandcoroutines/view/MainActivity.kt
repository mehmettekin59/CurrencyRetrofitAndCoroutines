package com.mehmettekin.currencyretrofitandcoroutines.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mehmettekin.currencyretrofitandcoroutines.R
import com.mehmettekin.currencyretrofitandcoroutines.adapter.CryptoAdapter
import com.mehmettekin.currencyretrofitandcoroutines.databinding.ActivityMainBinding
import com.mehmettekin.currencyretrofitandcoroutines.model.CryptoModel
import com.mehmettekin.currencyretrofitandcoroutines.service.CryptoAPI
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity(), CryptoAdapter.Listener {
    private lateinit var binding: ActivityMainBinding
    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModels: ArrayList<CryptoModel>? = null
    private var job: Job? = null
    private var recyclerViewAdapter : CryptoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recyclerView.layoutManager = LinearLayoutManager(this)

    }


    private fun loadData(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CryptoAPI::class.java)

        job = CoroutineScope(Dispatchers.IO).launch {
            val response = retrofit.getData()
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    response.body()?.let {
                        cryptoModels = ArrayList(it)
                        cryptoModels?.let {
                            recyclerViewAdapter = CryptoAdapter(it,this@MainActivity)
                            binding.recyclerView.adapter = recyclerViewAdapter
                        }
                    }
                }


            }
        }
    }

    override fun onItemClicked(cryptoModel: CryptoModel) {
        Toast.makeText(applicationContext," Clicked : ${cryptoModel.currency}",Toast.LENGTH_LONG).show()
        job?.cancel()
    }
}