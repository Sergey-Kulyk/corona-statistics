package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sergeykulyk.corona_statistics.data.dvo.OverviewStatistics

class OverviewViewModel : ViewModel() {

    val statisticsLiveData = MutableLiveData<List<OverviewStatistics>>()

    fun getStatistics() {
        val builder = OverviewStatisticsBuilder()
        val statistics = builder.build()
        statisticsLiveData.postValue(statistics)
    }
}