package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.data.dto.AllDto
import com.sergeykulyk.corona_statistics.data.dvo.OverviewStatistics
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

        val casesPercent = formatPercent(allDto.cases!!, allDto.cases!!)
        val activePercent = formatPercent(allDto.active!!, allDto.cases!!)
        val recoveredPercent = formatPercent(allDto.recovered!!, allDto.cases!!)
        val deathsPercent = formatPercent(allDto.deaths!!, allDto.cases!!)

        val cases = OverviewStatistics(
            R.drawable.ic_statistics_cases,
            R.string.statistics_cases,
            casesNumber,
            casesPercent
        )
        val active = OverviewStatistics(
            R.drawable.ic_statistics_active,
            R.string.statistics_active,
            activeNumber,
            activePercent
        )
        val recovered = OverviewStatistics(
            R.drawable.ic_statistics_recovered,
            R.string.statistics_recovered,
            recoveredNumber,
            recoveredPercent
        )
        val deaths = OverviewStatistics(
            R.drawable.ic_statistics_death,
            R.string.statistics_death,
            deathsNumber,
            deathsPercent
        )

        list.add(cases)
        list.add(active)
        list.add(recovered)
        list.add(deaths)

        return list
    }

    fun buildTest(): MutableList<OverviewStatistics> {
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

    private fun formatTotalNumber(number: Int?): String {
        val symbols = DecimalFormatSymbols()
        symbols.groupingSeparator = ','
        val myFormatter = DecimalFormat("###,###,###", symbols)
        return myFormatter.format(number ?: 0)
    }

    private fun formatPercent(new: Int, total: Int): String {
        return "${calculatePercentNew(new, total)}%"
    }

    private fun calculatePercentNew(cases: Int, total: Int): Int {
        return (cases * 100f / total).roundToInt()
    }
}