package com.tafh.covid_19app.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tafh.covid_19app.R
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import com.tafh.covid_19app.data.repository.CovidRepository
import com.tafh.covid_19app.databinding.FragmentDetailBinding
import com.tafh.covid_19app.ui.cariLokasi.CariLokasiViewModel
import com.tafh.covid_19app.ui.cariLokasi.CariLokasiViewModelFactory
import com.tafh.covid_19app.utils.Status

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DetailViewModel

    private var list = ArrayList<ProvinsiResponse>()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar!!.show()
        actionBar.title = ""

        recyclerView = initRecyclerViewSearchAkun()
        setupViewModel()

        setupObservers()




        return binding.root
    }

    private fun initRecyclerViewSearchAkun(): RecyclerView {

        recyclerView = binding.rvDetail

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        return recyclerView
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            DetailViewModelFactory(CovidRepository())
        ).get(DetailViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.covidProvinsi.observe(viewLifecycleOwner, { response ->
            response?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { provinsiResponse ->
                            retrieveList(provinsiResponse)
                        }
                    }


                }
            }
        })
    }

    private fun retrieveList(provinsiResponse: ArrayList<ProvinsiResponse>) {
        Log.i("DetailAdapter", "")
        list = provinsiResponse

        val adapter = DetailAdapter(list)
        recyclerView.adapter = adapter
    }




}