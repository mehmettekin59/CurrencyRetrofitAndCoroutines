package com.mehmettekin.currencyretrofitandcoroutines.service

import com.mehmettekin.currencyretrofitandcoroutines.model.CryptoModel
import retrofit2.Response
import retrofit2.http.GET

interface CryptoAPI {

    @GET("9e300b00e01124c032542c65e9d13075395f5ef6")
    suspend fun getData(): Response<List<CryptoModel>>


}