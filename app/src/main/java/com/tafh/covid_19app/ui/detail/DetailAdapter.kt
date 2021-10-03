package com.tafh.covid_19app.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import com.tafh.covid_19app.databinding.ItemProvinsiBinding

class DetailAdapter(private val list: List<ProvinsiResponse>) :
    RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProvinsiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(list: ProvinsiResponse) {
            binding.apply {
                tvNamaProvinsi.text = list.attributes.provinsi
                tvPositif.text = list.attributes.kasusPositif.toString()
                tvSembuh.text = list.attributes.kasusSembuh.toString()
                tvMeninggal.text = list.attributes.kasusMeninggal.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = ItemProvinsiBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


}