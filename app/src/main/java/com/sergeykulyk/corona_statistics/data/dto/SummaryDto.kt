package com.sergeykulyk.corona_statistics.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SummaryDto {

    @SerializedName("Global")
    @Expose
    var globalDto: GlobalDto? = null

    @SerializedName("Countries")
    @Expose
    var countries: List<CountryDto>? = null

}