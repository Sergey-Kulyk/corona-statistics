package com.corona.statistics.ui.dashboard.overview

import com.corona.statistics.R
import com.corona.statistics.data.dto.AllDto
import com.corona.statistics.data.dvo.OverviewStatistics
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.roundToInt

class OverviewStatisticsBuilder(private val allDto: AllDto) {

    fun build(): MutableList<OverviewStatistics> {
        val list = mutableListOf<OverviewStatistics>()

        val casesNumber = formatTotalNumber(allDto.cases)
        val activeNumber = formatTotalNumber(allDto.active)
        val recoveredNumber = formatTotalNumber(allDto.recovered)
        val deathsNumber = formatTotalNumber(allDto.deaths)

        val casesPercent = calculatePercentNew(allDto.cases!!, allDto.cases!!)
        val activePercent = calculatePercentNew(allDto.active!!, allDto.cases!!)
        val recoveredPercent = calculatePercentNew(allDto.recovered!!, allDto.cases!!)
        val deathsPercent = calculatePercentNew(allDto.deaths!!, allDto.cases!!)

        val casesPercentFormatted = formatPercent(casesPercent)
        val activePercentFormatted = formatPercent(activePercent)
        val recoveredPercentFormatted = formatPercent(recoveredPercent)
        val deathsPercentFormatted = formatPercent(deathsPercent)

        val cases = OverviewStatistics(
            R.drawable.ic_statistics_overview_cases,
            R.string.statistics_cases,
            casesNumber,
            casesPercentFormatted,
            casesPercent
        )
        val active = OverviewStatistics(
            R.drawable.ic_statistics_overview_active,
            R.string.statistics_active,
            activeNumber,
            activePercentFormatted,
            activePercent
        )
        val recovered = OverviewStatistics(
            R.drawable.ic_statistics_overview_recovered,
            R.string.statistics_recovered,
            recoveredNumber,
            recoveredPercentFormatted,
            recoveredPercent
        )
        val deaths = OverviewStatistics(
            R.drawable.ic_statistics_overview_death,
            R.string.statistics_death,
            deathsNumber,
            deathsPercentFormatted,
            deathsPercent
        )

        list.add(cases)
        list.add(active)
        list.add(recovered)
        list.add(deaths)

        return list
    }

//    fun buildTest(): MutableList<OverviewStatistics> {
//        val list = mutableListOf<OverviewStatistics>()
//
//        val cases = OverviewStatistics(
//            R.drawable.ic_statistics_overview_cases,
//            R.string.statistics_cases,
//            "463,342",
//            "99%"
//        )
//        val active = OverviewStatistics(
//            R.drawable.ic_statistics_overview_active,
//            R.string.statistics_active,
//            "328,629",
//            "96%"
//        )
//        val recovered = OverviewStatistics(
//            R.drawable.ic_statistics_overview_recovered,
//            R.string.statistics_recovered,
//            "113,802",
//            "84%"
//        )
//        val deaths = OverviewStatistics(
//            R.drawable.ic_statistics_overview_death,
//            R.string.statistics_death,
//            "20,911",
//            "16%"
//        )
//
//        list.add(cases)
//        list.add(active)
//        list.add(recovered)
//        list.add(deaths)
//
//        return list
//    }

    private fun formatTotalNumber(number: Int?): String {
        val symbols = DecimalFormatSymbols()
        symbols.groupingSeparator = ','
        val myFormatter = DecimalFormat("###,###,###", symbols)
        return myFormatter.format(number ?: 0)
    }

    private fun formatPercent(percent: Int): String {
        return "$percent%"
    }

    private fun calculatePercentNew(cases: Int, total: Int): Int {
        return (cases * 100f / total).roundToInt()
    }
}