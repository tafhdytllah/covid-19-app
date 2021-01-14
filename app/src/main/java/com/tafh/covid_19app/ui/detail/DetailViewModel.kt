package com.tafh.covid_19app.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import com.tafh.covid_19app.data.repository.CovidRepository
import com.tafh.covid_19app.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class DetailViewModel(private val covidRepository: CovidRepository) : ViewModel() {

    val covidProvinsi: MutableLiveData<Resource<ArrayList<ProvinsiResponse>>> = MutableLiveData()
    var covidProvinsiResponse = ArrayList<ProvinsiResponse>()

    init {
        getCovidProvinsi()
    }

    fun getCovidProvinsi() = viewModelScope.launch {
        safeCovidProvinsiCalls()
    }

    private suspend fun safeCovidProvinsiCalls() {
        covidProvinsi.postValue(Resource.loading(data = null))

        try {
            val response = covidRepository.getCovidProvinsi()
            covidProvinsi.postValue(handleCovidProvinsiResponse(response))
        } catch (exception: Exception) {
            covidProvinsi.postValue(Resource.error(data = null, message = exception.message ?: "Error Occured!"))
        }
    }

    private fun handleCovidProvinsiResponse(
            response: Response<ArrayList<ProvinsiResponse>>
    ): Resource<ArrayList<ProvinsiResponse>>? {

        if (response.isSuccessful) {
            response.body()?.let {
                return Resource.success(data = it)
            }
        }
        return Resource.error(data = null, message = response.message() ?: "Error Occured!")
    }

}