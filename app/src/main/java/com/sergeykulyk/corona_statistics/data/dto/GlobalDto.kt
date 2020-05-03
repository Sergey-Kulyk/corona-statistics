package com.sergeykulyk.corona_statistics.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GlobalDto {

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
    
    fun calculateActive(): Int {
        return (totalConfirmed ?: 0) - (totalRecovered ?: 0) - (totalDeaths ?: 0)
    }

    fun calculateNewActive(): Int {
        return (newConfirmed ?: 0) - (newDeaths ?: 0) - (newRecovered ?: 0)
    }

}