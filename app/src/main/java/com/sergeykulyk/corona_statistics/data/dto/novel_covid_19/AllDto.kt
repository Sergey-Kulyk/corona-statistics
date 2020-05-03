package com.sergeykulyk.corona_statistics.data.dto.novel_covid_19

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllDto {

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

    @SerializedName("casesPerOneMillion")
    @Expose
    var casesPerOneMillion: Int? = null

    @SerializedName("deathsPerOneMillion")
    @Expose
    var deathsPerOneMillion: Int? = null

    @SerializedName("tests")
    @Expose
    var tests: Int? = null

    @SerializedName("testsPerOneMillion")
    @Expose
    var testsPerOneMillion: Double? = null

    @SerializedName("affectedCountries")
    @Expose
    var affectedCountries: Int? = null

}