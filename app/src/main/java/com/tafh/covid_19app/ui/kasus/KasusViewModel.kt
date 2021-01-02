package com.tafh.covid_19app.ui.kasus

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tafh.covid_19app.data.network.api.RetrofitInstance
import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KasusViewModel : ViewModel() {

    private val _positif = MutableLiveData<String>()
    val positif: LiveData<String> get() = _positif

    private val _sembuh = MutableLiveData<String>()
    val sembuh: LiveData<String> get() = _sembuh

    private val _meninggal = MutableLiveData<String>()
    val meninggal: LiveData<String> get() = _meninggal

    fun makeApiCall() {
        val service = RetrofitInstance

        service.endpoint.getIndonesia().enqueue(object : Callback<List<IndonesiaResponse>> {
            override fun onResponse(
                call: Call<List<IndonesiaResponse>>,
                response: Response<List<IndonesiaResponse>>
            ) {
                Log.d("MainActivity", "Response success")
                if (response.isSuccessful) {
                    val indonesiaResponse: List<IndonesiaResponse> = response.body()!!
                    setResponse( indonesiaResponse )
                }
            }

            override fun onFailure(call: Call<List<IndonesiaResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun setResponse(indonesiaResponse: List<IndonesiaResponse>) {
        val response = indonesiaResponse[0]
        val death = response.meninggal
        val recovery = response.sembuh
        val positive = response.positif

        _positif.value = positive
        _sembuh.value = recovery
        _meninggal.value = death

    }
}