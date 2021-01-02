package com.tafh.covid_19app.ui.kasus

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.tafh.covid_19app.R
import com.tafh.covid_19app.data.network.api.RetrofitInstance
import com.tafh.covid_19app.databinding.FragmentKasusBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.await


class KasusFragment : Fragment() {

    private val viewModel: KasusViewModel by viewModels()
    private lateinit var binding: FragmentKasusBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_kasus, container, false)

        binding.kasusViewModel = viewModel
        binding.setLifecycleOwner(this)

        viewModel.makeApiCall()

        return binding.root
    }




}