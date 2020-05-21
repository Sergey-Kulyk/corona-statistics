package com.corona.statistics.data.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.corona.map_statistics.ContinentStatistics

class ContinentDto {

    @SerializedName("updated")
    @Expose
    var updated: Long? = null

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

    @SerializedName("continent")
    @Expose
    var continent: String? = null

    @SerializedName("countries")
    @Expose
    var countries: List<String>? = null

}

fun ContinentDto.toContinentStatistics() = ContinentStatistics(continent ?: "", cases ?: 0)