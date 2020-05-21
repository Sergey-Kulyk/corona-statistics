package com.corona.statistics.io.rest

import com.corona.statistics.data.dto.AllDto
import com.corona.statistics.data.dto.ContinentDto
import com.corona.statistics.data.dto.CountryDto
import com.corona.statistics.data.dto.historical_data.CountryTimelineDto
import retrofit2.http.GET
import retrofit2.http.Query

interface Covid19Service {

    @GET("all")
    suspend fun getAll(): AllDto

    @GET("continents?sort=true")
    suspend fun getContinents(): List<ContinentDto>

    @GET("historical")
    suspend fun getHistoricalTimeline(@Query("lastdays") lastDays: Int = 100): List<CountryTimelineDto>

    @GET("countries?sort=cases")
    suspend fun getAllCounties(): List<CountryDto>

}