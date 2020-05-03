package com.sergeykulyk.corona_statistics.io.rest

import com.sergeykulyk.corona_statistics.data.dto.novel_covid_19.AllDto
import com.sergeykulyk.corona_statistics.data.dto.novel_covid_19.CountryDto
import retrofit2.Call
import retrofit2.http.GET

interface NovelCovid19Service {

    @GET("countries")
    fun getMultipleCountries(): Call<List<CountryDto>>

    @GET("all")
    fun getAll(): Call<AllDto>

}