package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.data.cache.Cache
import com.sergeykulyk.corona_statistics.data.dto.toContinentStatistics
import com.sergeykulyk.corona_statistics.data.dvo.OverviewStatistics
import com.sergeykulyk.corona_statistics.io.repo.StatisticsRepo
import com.sergeykulyk.corona_statistics.ui.ToastHelper
import com.sergeykulyk.map_statistics.ContinentStatistics
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class OverviewViewModel : ViewModel() {

    val statisticsLiveData = MutableLiveData<List<OverviewStatistics>>()
    val mapCountriesLiveData = MutableLiveData<List<ContinentStatistics>>()

    private val statisticsRepo = StatisticsRepo()

    fun getStatistics() {
        viewModelScope.launch {
            try {
                val continentsStatics = statisticsRepo.fetchContinentsStatics()
                val allStatics = statisticsRepo.fetchAllStatics()

                val mapCountries = continentsStatics.map { it.toContinentStatistics() }
                mapCountriesLiveData.postValue(mapCountries)

                val builder = OverviewStatisticsBuilder(allStatics)
                val overviewStatistics = builder.build()

                Cache.overviewStatistics = overviewStatistics
                statisticsLiveData.postValue(overviewStatistics)
            } catch (e: Exception) {
                if (
                    e is UnknownHostException ||
                    e is SocketException ||
                    e is SocketTimeoutException ||
                    e is ConnectException
                ) {
                    ToastHelper.showToast(R.string.intener_connection_error)
                } else if (
                    e is HttpException ||
                    e is InterruptedIOException
                ) {
                    ToastHelper.showToast(R.string.intener_server_error)
                } else {
                    ToastHelper.showToast(R.string.intener_something_went_wrong)
                }
            }
        }
    }
}