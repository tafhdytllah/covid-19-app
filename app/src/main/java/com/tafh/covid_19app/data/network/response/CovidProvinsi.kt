package com.tafh.covid_19app.data.network.response


import com.google.gson.annotations.SerializedName

data class CovidProvinsi(
    @SerializedName("FID")
    val fID: Int,
    @SerializedName("Kasus_Meni")
    val kasusMeninggal: Int,
    @SerializedName("Kasus_Posi")
    val kasusPositif: Int,
    @SerializedName("Kasus_Semb")
    val kasusSembuh: Int,
    @SerializedName("Kode_Provi")
    val kodeProvinsi: Int,
    @SerializedName("Provinsi")
    val provinsi: String
)