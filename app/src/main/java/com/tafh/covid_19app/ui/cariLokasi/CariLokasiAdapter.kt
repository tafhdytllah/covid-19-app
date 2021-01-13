package com.tafh.covid_19app.ui.cariLokasi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tafh.covid_19app.data.network.response.ProvinsiResponse
import com.tafh.covid_19app.databinding.ItemProvinsiBinding

class CariLokasiAdapter(
        private val list: ArrayList<ProvinsiResponse>
) : RecyclerView.Adapter<CariLokasiAdapter.CariLokasiViewHolder>() {

    inner class CariLokasiViewHolder(private val binding: ItemProvinsiBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(provinsi: ProvinsiResponse) {
            binding.apply {
                tvNamaProvinsi.text = provinsi.attributes.provinsi
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CariLokasiViewHolder {
        val view = ItemProvinsiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CariLokasiViewHolder(view)
    }

    override fun onBindViewHolder(holder: CariLokasiViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}