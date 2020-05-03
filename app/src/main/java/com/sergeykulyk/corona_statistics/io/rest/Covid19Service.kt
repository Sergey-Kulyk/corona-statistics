package com.sergeykulyk.corona_statistics.io.rest

import com.sergeykulyk.corona_statistics.data.dto.SummaryDto
import retrofit2.Call
import retrofit2.http.GET

interface Covid19Service {

    @GET("/summary")
    fun getSummary(): Call<SummaryDto>

}