package com.tafh.covid_19app.ui.cariLokasi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tafh.covid_19app.data.repository.CovidRepository
import java.lang.IllegalArgumentException

class CariLokasiViewModelFactory(private val covidRepository: CovidRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CariLokasiViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CariLokasiViewModel(covidRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}