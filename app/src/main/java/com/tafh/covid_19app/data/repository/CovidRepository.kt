package com.tafh.covid_19app.data.repository

import com.tafh.covid_19app.data.network.api.RetrofitBuilder.apiService
import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import retrofit2.Response

class CovidRepository {

    suspend fun getCovidIndonesia(): Response<List<IndonesiaResponse>> {
      return apiService.getIndonesia()
    }
    suspend fun getCovidProvinsi(): Response<List<ProvinsiResponse>> {
        return apiService.getProvinsi()
    }

}