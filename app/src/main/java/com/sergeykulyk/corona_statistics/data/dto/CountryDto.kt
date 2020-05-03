package com.sergeykulyk.corona_statistics.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sergeykulyk.map_statistics.MapCountry

class CountryDto {

    @SerializedName("Country")
    @Expose
    var country: String? = null

    @SerializedName("CountryCode")
    @Expose
    var countryCode: String? = null

    @SerializedName("Slug")
    @Expose
    var slug: String? = null

    @SerializedName("NewConfirmed")
    @Expose
    var newConfirmed: Int? = null

    @SerializedName("TotalConfirmed")
    @Expose
    var totalConfirmed: Int? = null

    @SerializedName("NewDeaths")
    @Expose
    var newDeaths: Int? = null

    @SerializedName("TotalDeaths")
    @Expose
    var totalDeaths: Int? = null

    @SerializedName("NewRecovered")
    @Expose
    var newRecovered: Int? = null

    @SerializedName("TotalRecovered")
    @Expose
    var totalRecovered: Int? = null

    @SerializedName("Date")
    @Expose
    var date: String? = null

}

fun CountryDto.toMapCountry() = MapCountry(slug ?: "", totalConfirmed ?: 0)