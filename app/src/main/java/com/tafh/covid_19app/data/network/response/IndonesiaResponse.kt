package com.tafh.covid_19app.data.network.response

import com.google.gson.annotations.SerializedName


data class IndonesiaResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("positif")
    val positif: String,
    @SerializedName("sembuh")
    val sembuh: String,
    @SerializedName("meninggal")
    val meninggal: String,
    @SerializedName("dirawat")
    val dirawat: String,

)