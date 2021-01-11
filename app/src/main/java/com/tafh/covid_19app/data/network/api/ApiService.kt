package com.tafh.covid_19app.data.network.api

import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import retrofit2.Response

import retrofit2.http.GET

interface ApiService {

    /**
     * covid api
     * base url = https://api.kawalcorona.com/
     * endpoint data indonesia = indonesia
     * endpoint data provinsi = indonesia/provinsi
     */

    @GET("indonesia")
    suspend fun getIndonesia(): Response<List<IndonesiaResponse>>

    @GET("indonesia/provinsi")
    fun getProvinsi(): Response<List<ProvinsiResponse>>
}