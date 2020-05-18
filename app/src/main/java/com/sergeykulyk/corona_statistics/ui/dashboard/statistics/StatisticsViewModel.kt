package com.sergeykulyk.corona_statistics.ui.dashboard.statistics

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.data.cache.Cache
import com.sergeykulyk.corona_statistics.data.dvo.CountryStatisticsDvo
import com.sergeykulyk.corona_statistics.io.repo.StatisticsRepo
import com.sergeykulyk.corona_statistics.ui.ToastHelper
import com.sergeykulyk.corona_statistics.ui.dashboard.overview.OverviewStatisticsBuilder
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*
import kotlin.collections.HashMap

class StatisticsViewModel : ViewModel() {

    val countriesStatisticsLiveData = MutableLiveData<List<CountryStatisticsDvo>>()
    val dayWorldwideStatisticsLiveData = MutableLiveData<HashMap<String, Int>>()
    val isPlaceHolderShowingLiveData = MutableLiveData<Boolean>()

    private val statisticsRepo = StatisticsRepo()

    private val historicalDayBuilder = HistoricalDayBuilder()

    fun getCountryStatistics() {
        viewModelScope.launch {
            try {
                if (Cache.overviewStatistics == null) {
                    val allStatistics = statisticsRepo.fetchAllStatics()

                    val builder = OverviewStatisticsBuilder(allStatistics)
                    val overviewStatistics = builder.build()
                    Cache.overviewStatistics = overviewStatistics
                }
                val historicalTimeline = statisticsRepo.fetchHistoricalTimelineStatics()
                val allCountries = statisticsRepo.fetchAllCountriesStatics()

                historicalDayBuilder.historicalData = historicalTimeline
                historicalDayBuilder.allCountries = allCountries

            } catch (e: Exception) {
                e.printStackTrace()
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

    fun getHistoricalForDay(selectedDay: Calendar) {
        if (historicalDayBuilder.historicalData == null) {
            getCountryStatistics()
            return
        }
        viewModelScope.launch {
            if (historicalDayBuilder.hasDataForDay(selectedDay)) {
                val countriesStatistics = if (historicalDayBuilder.isCurrentDay(selectedDay)) {
                    historicalDayBuilder.getCurrentDayStatistics()
                } else {
                    historicalDayBuilder.getStatisticsForPreviousDay(selectedDay)
                }

                val worldwideStatistics = historicalDayBuilder.getWorldWideStatistics(selectedDay)

                isPlaceHolderShowingLiveData.postValue(false)

                countriesStatisticsLiveData.postValue(countriesStatistics)
                dayWorldwideStatisticsLiveData.postValue(worldwideStatistics)
            } else {
                isPlaceHolderShowingLiveData.postValue(true)
            }
        }
    }

}