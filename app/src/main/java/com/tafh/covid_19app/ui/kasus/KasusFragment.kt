package com.tafh.covid_19app.ui.kasus

import android.app.ActionBar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActionBarContainer
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.tafh.covid_19app.R
import com.tafh.covid_19app.data.network.response.IndonesiaResponse
import com.tafh.covid_19app.data.repository.CovidRepository
import com.tafh.covid_19app.databinding.FragmentKasusBinding
import com.tafh.covid_19app.ui.CovidViewModel
import com.tafh.covid_19app.ui.CovidViewModelFactory
import com.tafh.covid_19app.utils.Status


class KasusFragment : Fragment(R.layout.fragment_kasus) {

    private var _binding: FragmentKasusBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CovidViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKasusBinding.inflate(inflater, container, false)

        (activity as AppCompatActivity).supportActionBar?.hide()

        setupViewModel()
        showProgressBar()

        viewModel.covidIndoResponse.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                val covidIndonesia = response.body()?.get(0)!!
                hideProgressBar()
                binding.apply {
                    tvPositive.text = covidIndonesia.positif
                    tvRecover.text = covidIndonesia.sembuh
                    tvDeath.text = covidIndonesia.meninggal
                }

            } else {
                hideProgressBar()
                Toast.makeText(requireContext(), "${response.errorBody()}", Toast.LENGTH_SHORT).show()
            }

        })

//        setupObservers()

        binding.etCarilokasi.setOnClickListener { view ->

            val actiion = KasusFragmentDirections.actionKasusFragmentToCariLokasiFragment()
            view.findNavController().navigate(actiion)

        }

        binding.tvDetail.setOnClickListener { view ->
            val action = KasusFragmentDirections.actionKasusFragmentToDetailFragment()
            view.findNavController().navigate(action)
        }

        return binding.root
    }


    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            CovidViewModelFactory(CovidRepository())
        ).get(CovidViewModel::class.java)
    }

//    private fun setupObservers() {
//        viewModel.covidIndonesia.observe(viewLifecycleOwner, Observer { response ->
//            response?.let { resource ->
//                when (resource.status) {
//                    Status.SUCCESS -> {
//                        hideProgressBar()
//                        resource.data?.let { indonesiaResponse ->
//                            retrieveList(indonesiaResponse)
//                        }
//                    }
//                    Status.ERROR -> {
//                        hideProgressBar()
//                        response.message.let { message ->
//                            Toast.makeText(context, "an error accured: $message", Toast.LENGTH_LONG)
//                                .show()
//                        }
//                    }
//                    Status.LOADING -> {
//                        showProgressBar()
//                    }
//                }
//            }
//        })
//    }


//    private fun retrieveList(indonesiaResponse: IndonesiaResponse) {
//        Log.i("KasusFragment", "$indonesiaResponse")
//        val response = indonesiaResponse
//        binding.apply {
//            tvPositive.text = response.positif
//            tvRecover.text = response.sembuh
//            tvDeath.text = response.meninggal
//        }
//    }

    private fun hideProgressBar() {
        binding.kasusProgressBar.visibility = View.INVISIBLE

    }

    private fun showProgressBar() {
        binding.kasusProgressBar.visibility = View.VISIBLE

    }


}