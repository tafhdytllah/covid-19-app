package com.tafh.covid_19app.ui.kasus

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import com.tafh.covid_19app.data.repository.CovidRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class KasusViewModel(private val repository: CovidRepository) : ViewModel() {

    val covidResponse: MutableLiveData<Response<List<IndonesiaResponse>>> = MutableLiveData()

    init {
        getCovidIndonesia()
    }
    fun getCovidIndonesia() {
        viewModelScope.launch {
            val response = repository.getCovidIndonesia()
            covidResponse.value = response
        }
    }


//    val covidIndonesia: MutableLiveData<Resource<IndonesiaResponse>> by lazy { MutableLiveData() }
//    var covidIndonesiaResponse: IndonesiaResponse? = null
//
//    init {
//        getCovidIndonesia()
//    }
//
//    fun getCovidIndonesia() = viewModelScope.launch {
//        safeCovidIndonesiaCalls()
//    }
//
//
//    private suspend fun safeCovidIndonesiaCalls() {
//        covidIndonesia.postValue(Resource.loading(data = null))
//
//        try {
//            val response = repository.getCovidIndonesia()
//            covidIndonesia.postValue(handleCovidIndonesiaResponse(response))
//        } catch (exception: Exception) {
//            covidIndonesia.postValue(Resource.error(data = null, message = exception.message ?: "Error Occured!"))
//        }
//    }
//
//    private fun handleCovidIndonesiaResponse(response: Response<List<IndonesiaResponse>>): Resource<IndonesiaResponse>? {
//
//        if (response.isSuccessful) {
//            response.body()?.let {
//                val resultResponse = it[0]
//                if (covidIndonesiaResponse == null) {
//                    covidIndonesiaResponse = resultResponse
//                } else {
//                    val listOld = mutableListOf(
//                            covidIndonesiaResponse?.dirawat,
//                            covidIndonesiaResponse?.meninggal,
//                            covidIndonesiaResponse?.name,
//                            covidIndonesiaResponse?.positif,
//                            covidIndonesiaResponse?.sembuh
//                    )
//                    val listNew = mutableListOf(
//                            resultResponse.dirawat,
//                            resultResponse.meninggal,
//                            resultResponse.name,
//                            resultResponse.positif,
//                            resultResponse.sembuh
//                    )
//                    with(listOld) {
//                        clear()
//                        addAll(listNew)
//                    }
//                }
//                return Resource.success(data = covidIndonesiaResponse ?: resultResponse)
//            }
//        }
//        return Resource.error(data = null, message = response.message() ?: "Error Occured!")
//    }

}