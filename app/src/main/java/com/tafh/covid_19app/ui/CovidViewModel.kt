package com.tafh.covid_19app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import com.tafh.covid_19app.data.repository.CovidRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class CovidViewModel(private val repository: CovidRepository): ViewModel() {

    val covidIndoResponse: MutableLiveData<Response<List<IndonesiaResponse>>> = MutableLiveData()
    val covidProvinsiResponse: MutableLiveData<Response<List<ProvinsiResponse>>> = MutableLiveData()

    fun getCovidIndonesia() {
        viewModelScope.launch {
            val response = repository.getCovidIndonesia()
            covidIndoResponse.value = response
        }
    }
    fun getCovidProvinsi() {
        viewModelScope.launch {
            val response = repository.getCovidProvinsi()
            covidProvinsiResponse.value = response
        }
    }


}