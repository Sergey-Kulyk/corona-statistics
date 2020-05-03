package com.sergeykulyk.corona_statistics.io.rest

import com.sergeykulyk.corona_statistics.data.dto.AllDto
import com.sergeykulyk.corona_statistics.data.dto.ContinentDto
import com.sergeykulyk.corona_statistics.data.dto.CountryDto
import retrofit2.Call
import retrofit2.http.GET

interface Covid19Service {

    @GET("countries")
    fun getMultipleCountries(): Call<List<CountryDto>>

    @GET("all")
    fun getAll(): Call<AllDto>

    @GET("continents?sort=true")
    fun getContinents(): Call<List<ContinentDto>>

}