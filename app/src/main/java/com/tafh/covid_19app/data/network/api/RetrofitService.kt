package com.tafh.covid_19app.data.network.api

import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    //covid api
    //base url = https://api.kawalcorona.com/
    //endpoint data indonesia = indonesia
    //endpoint data provinsi = indonesia/provinsi

    @GET("indonesia")
    fun getIndonesia(): Call<List<IndonesiaResponse>>

    @GET("indonesia/provinsi")
    fun getProvinsi(): Call<List<ProvinsiResponse>>
}