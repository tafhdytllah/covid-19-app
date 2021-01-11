package com.tafh.covid_19app.ui.kasus

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tafh.covid_19app.data.repository.CovidRepository
import java.lang.IllegalArgumentException

class KasusViewModelFactory(private val covidRepository: CovidRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(KasusViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return KasusViewModel(covidRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}