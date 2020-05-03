package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergeykulyk.corona_statistics.data.dto.GlobalDto
import com.sergeykulyk.corona_statistics.data.dto.SummaryDto
import com.sergeykulyk.corona_statistics.data.dto.novel_covid_19.AllDto
import com.sergeykulyk.corona_statistics.data.dto.novel_covid_19.CountryDto
import com.sergeykulyk.corona_statistics.data.dto.novel_covid_19.toMapCountry
import com.sergeykulyk.corona_statistics.data.dto.toMapCountry
import com.sergeykulyk.corona_statistics.data.dvo.OverviewStatistics
import com.sergeykulyk.corona_statistics.io.rest.Rest
import com.sergeykulyk.map_statistics.MapCountry
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    val statisticsLiveData = MutableLiveData<List<OverviewStatistics>>()
    val mapCountriesLiveData = MutableLiveData<List<MapCountry>>()

    fun getStatistics() {
        val result = Rest.novelCovidApi.getMultipleCountries()
        result.enqueue(object : Callback<List<CountryDto>> {
            override fun onFailure(call: Call<List<CountryDto>>, t: Throwable) {
                t.message
            }

            override fun onResponse(call: Call<List<CountryDto>>, response: Response<List<CountryDto>>) {
                response.body()?.let { body ->
                    val mapCountries = body.map { it.toMapCountry() }
                    mapCountriesLiveData.postValue(mapCountries)
                }
            }
        })

        val result1 = Rest.novelCovidApi.getAll()
        result1.enqueue(object : Callback<AllDto> {
            override fun onFailure(call: Call<AllDto>, t: Throwable) {
                t.message
            }

            override fun onResponse(call: Call<AllDto>, response: Response<AllDto>) {
                response.body()?.let { body ->
                    val builder = OverviewStatisticsBuilder(body)
                    val statistics = builder.build()
                    statisticsLiveData.postValue(statistics)
                }
            }
        })

    }
}