package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergeykulyk.corona_statistics.data.dto.AllDto
import com.sergeykulyk.corona_statistics.data.dto.ContinentDto
import com.sergeykulyk.corona_statistics.data.dto.toContinentStatistics
import com.sergeykulyk.corona_statistics.data.dvo.OverviewStatistics
import com.sergeykulyk.corona_statistics.io.rest.Rest
import com.sergeykulyk.map_statistics.ContinentStatistics
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    val statisticsLiveData = MutableLiveData<List<OverviewStatistics>>()
    val mapCountriesLiveData = MutableLiveData<List<ContinentStatistics>>()

    fun getStatistics() {
        val result = Rest.covid19.getContinents()
        result.enqueue(object : Callback<List<ContinentDto>> {
            override fun onFailure(call: Call<List<ContinentDto>>, t: Throwable) {
                t.message
            }

            override fun onResponse(call: Call<List<ContinentDto>>, response: Response<List<ContinentDto>>) {
                response.body()?.let { body ->
                    val mapCountries = body.map { it.toContinentStatistics() }
                    mapCountriesLiveData.postValue(mapCountries)
                }
            }
        })

        val result1 = Rest.covid19.getAll()
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