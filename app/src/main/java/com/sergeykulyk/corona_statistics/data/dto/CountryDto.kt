package com.sergeykulyk.corona_statistics.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sergeykulyk.map_statistics.MapCountry

class CountryDto {

    @SerializedName("updated")
    @Expose
    var updated: Long? = null

    @SerializedName("country")
    @Expose
    var country: String? = null

    @SerializedName("countryInfo")
    @Expose
    var countryInfoDto: CountryInfoDto? = null

    @SerializedName("cases")
    @Expose
    var cases: Int? = null

    @SerializedName("todayCases")
    @Expose
    var todayCases: Int? = null

    @SerializedName("deaths")
    @Expose
    var deaths: Int? = null

    @SerializedName("todayDeaths")
    @Expose
    var todayDeaths: Int? = null

    @SerializedName("recovered")
    @Expose
    var recovered: Int? = null

    @SerializedName("active")
    @Expose
    var active: Int? = null

    @SerializedName("critical")
    @Expose
    var critical: Int? = null

    @SerializedName("casesPerOneMillion")
    @Expose
    var casesPerOneMillion: Double? = null

    @SerializedName("deathsPerOneMillion")
    @Expose
    var deathsPerOneMillion: Double? = null

    @SerializedName("tests")
    @Expose
    var tests: Int? = null

    @SerializedName("testsPerOneMillion")
    @Expose
    var testsPerOneMillion: Int? = null

    @SerializedName("continent")
    @Expose
    var continent: String? = null

}

fun CountryDto.toMapCountry() = MapCountry(country?.toLowerCase() ?: "", cases ?: 0)
