package com.tafh.covid_19app.data.repository

import com.tafh.covid_19app.data.network.api.RetrofitBuilder.apiService

class CovidRepository {

    suspend fun getCovidIndonesia() = apiService.getIndonesia()

    suspend fun getCovidProvinsi() = apiService.getProvinsi()

}