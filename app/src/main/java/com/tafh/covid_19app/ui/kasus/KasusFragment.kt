package com.tafh.covid_19app.ui.kasus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.tafh.covid_19app.R
import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import com.tafh.covid_19app.data.repository.CovidRepository
import com.tafh.covid_19app.databinding.FragmentKasusBinding


class KasusFragment : Fragment(R.layout.fragment_kasus) {

    private lateinit var binding: FragmentKasusBinding
    private lateinit var viewModel: KasusViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentKasusBinding.inflate(inflater, container, false)
        setupViewModel()
        showProgressBar()
        setupObservers()

        return binding.root
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
                this,
                KasusViewModelFactory(CovidRepository())
        ).get(KasusViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.covidIndonesia.observe(viewLifecycleOwner, Observer { response ->
            response?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        hideProgressBar()
                        resource.data?.let { indonesiaResponse ->
                            retrieveList(indonesiaResponse)
                        }
                    }
                    Status.ERROR -> {
                        hideProgressBar()
                        response.message.let { message ->
                            Toast.makeText(context, "an error accured: $message", Toast.LENGTH_LONG).show()
                        }
                    }
                    Status.LOADING -> {
                        showProgressBar()
                    }
                }
            }
        })
    }


    private fun retrieveList(indonesiaResponse: IndonesiaResponse) {
        Log.i("KasusFragment", "$indonesiaResponse")
        val response = indonesiaResponse
        binding.apply {
            tvPositive.text = response.positif
            tvRecover.text = response.sembuh
            tvDeath.text = response.meninggal
        }
    }

    private fun hideProgressBar() {
        binding.kasusProgressBar.visibility = View.INVISIBLE

    }

    private fun showProgressBar() {
        binding.kasusProgressBar.visibility = View.VISIBLE

    }




}