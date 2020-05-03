package com.sergeykulyk.corona_statistics.data.dto.novel_covid_19

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CountryInfoDto {

    @SerializedName("_id")
    @Expose
    var id: Int? = null

    @SerializedName("iso2")
    @Expose
    var iso2: String? = null

    @SerializedName("iso3")
    @Expose
    var iso3: String? = null

    @SerializedName("lat")
    @Expose
    var lat: Double? = null

    @SerializedName("long")
    @Expose
    var long: Double? = null

    @SerializedName("flag")
    @Expose
    var flag: String? = null

}

