package com.tafh.covid_19app.ui.cariLokasi

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tafh.covid_19app.R
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import com.tafh.covid_19app.data.repository.CovidRepository
import com.tafh.covid_19app.databinding.FragmentCariLokasiBinding
import com.tafh.covid_19app.utils.Status
import java.util.*
import kotlin.collections.ArrayList


class CariLokasiFragment : Fragment(R.layout.fragment_cari_lokasi) {

    private var _binding: FragmentCariLokasiBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CariLokasiViewModel
    private var provinsiList = emptyList<ProvinsiResponse>()

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCariLokasiBinding.inflate(inflater, container, false)

        val actionBar = (activity as AppCompatActivity).supportActionBar
        actionBar!!.show()
        actionBar.title = ""

        setupViewModel()
        setupObservers()

        recyclerView = initRecyclerViewSearchAkun()

        binding.etCariLokasi.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                filterList(s.toString().toUpperCase(Locale.ROOT))
            }

        })



        return binding.root
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



    private fun setupViewModel() {
        viewModel = ViewModelProvider(
                this,
                CariLokasiViewModelFactory(CovidRepository())
        ).get(CariLokasiViewModel::class.java)
    }

    private fun retrieveList(provinsiResponse: List<ProvinsiResponse>) {

        provinsiList = provinsiResponse

    }

    private fun filterList(filterItem: String) {
        val filterList = ArrayList<ProvinsiResponse>()

        for (list in provinsiList) {
            if (filterItem in list.attributes.provinsi.toUpperCase(Locale.ROOT)) {
                filterList.add(list)
            }
        }


        val adapter = CariLokasiAdapter(filterList)
        recyclerView.adapter = adapter

    }

    private fun initRecyclerViewSearchAkun(): RecyclerView {

        recyclerView = binding.rvCariLokasi

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

        return recyclerView
    }










}
