package com.tafh.covid_19app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tafh.covid_19app.data.repository.CovidRepository
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val covidRepository: CovidRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailViewModel(covidRepository) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }

}