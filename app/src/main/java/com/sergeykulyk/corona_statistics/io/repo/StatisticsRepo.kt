package com.sergeykulyk.corona_statistics.io.repo

import com.sergeykulyk.corona_statistics.io.rest.Rest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StatisticsRepo {

    suspend fun fetchAllStatics() = withContext(Dispatchers.Default) { Rest.covid19.getAll() }

    suspend fun fetchContinentsStatics() = withContext(Dispatchers.Default) { Rest.covid19.getContinents() }

    suspend fun fetchHistoricalTimelineStatics() = withContext(Dispatchers.Default) { Rest.covid19.getHistoricalTimeline() }

    suspend fun fetchAllCountriesStatics() = withContext(Dispatchers.Default) { Rest.covid19.getAllCounties() }
}