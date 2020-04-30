package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.data.dvo.OverviewStatistics

class OverviewStatisticsBuilder() {

    fun build(): MutableList<OverviewStatistics> {
        val list = mutableListOf<OverviewStatistics>()

        val cases = OverviewStatistics(
            R.drawable.ic_statistics_cases,
            R.string.statistics_cases,
            "463,342",
            "99%"
        )
        val active = OverviewStatistics(
            R.drawable.ic_statistics_active,
            R.string.statistics_active,
            "328,629",
            "96%"
        )
        val recovered = OverviewStatistics(
            R.drawable.ic_statistics_recovered,
            R.string.statistics_recovered,
            "113,802",
            "84%"
        )
        val deaths = OverviewStatistics(
            R.drawable.ic_statistics_death,
            R.string.statistics_death,
            "20,911",
            "16%"
        )

        list.add(cases)
        list.add(active)
        list.add(recovered)
        list.add(deaths)

        return list
    }
}