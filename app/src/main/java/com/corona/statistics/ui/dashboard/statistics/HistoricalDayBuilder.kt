package com.corona.statistics.ui.dashboard.statistics

import android.annotation.SuppressLint
import com.corona.statistics.R
import com.corona.statistics.data.dto.CountryDto
import com.corona.statistics.data.dto.historical_data.CountryTimelineDto
import com.corona.statistics.data.dvo.CountryStatisticsDvo
import com.corona.statistics.getAppContext
import com.corona.statistics.getResources
import com.corona.statistics.utils.CountryCodeHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap


class HistoricalDayBuilder {

    var historicalData: List<CountryTimelineDto>? = null
    var allCountries: List<CountryDto>? = null

    suspend fun hasDataForDay(calendar: Calendar): Boolean {
        val formattedDay = formatCalendar(calendar)
        return withContext(Dispatchers.Default) {
            hasDataForTimeline(formattedDay) || hasDataForCurrentDay(calendar)
        }
    }

    private fun hasDataForTimeline(day: String): Boolean {
        return !historicalData.isNullOrEmpty() && historicalData!![0].timeline?.casesDto?.contains(
            day
        ) ?: false
    }

    private fun hasDataForCurrentDay(calendar: Calendar) =
        isCurrentDay(calendar) && allCountries != null

    suspend fun getStatisticsForPreviousDay(calendar: Calendar): List<CountryStatisticsDvo> {
        return supervisorScope {

            // remove empty list for
            if (hasCountriesStatisticsData()) {
                emptyList<CountryStatisticsDvo>()
            }

            val formattedDay = formatCalendar(calendar)

            // Get day country statistics
            val dayCountriesStatistics = getDayCountryStatistics(formattedDay)
            val myCountryDto = getMyCountryStatistics()

            // Find my country statistics.
            val myCountryStatistics =
                dayCountriesStatistics.find { it.name == myCountryDto?.country }

            // Move my country to top
            myCountryStatistics?.let {
                dayCountriesStatistics.remove(myCountryStatistics)
                dayCountriesStatistics.add(0, myCountryStatistics)
            }

            // Add worldwide statistics
            val worldwide = buildWorldwideStatistics(formattedDay)
            dayCountriesStatistics.add(0, worldwide)

            dayCountriesStatistics
        }
    }

    private fun getDayCountryStatistics(selectedDay: String): MutableList<CountryStatisticsDvo> {
        return allCountries?.mapNotNull { country ->
            val countryTimeline =
                historicalData?.find { countryTimelineDto ->
                    countryTimelineDto.country == country.country
                }
            countryTimeline?.let { buildCountryStatisticDvo(it, country, selectedDay) }
        }?.toMutableList() ?: mutableListOf()
    }

    private fun getMyCountryStatistics(): CountryDto? {
        // Get iso for detect user country
        val myCountryIso2 = CountryCodeHelper.getDeviceCountryCode(getAppContext())
        val myCountryIso2Upper = myCountryIso2?.toUpperCase(Locale.getDefault())

        return allCountries?.find { it.countryInfoDto?.iso2 ?: "" == myCountryIso2Upper }
    }

    fun getCurrentDayStatistics(): MutableList<CountryStatisticsDvo> {
        val countriesStatistics = getCurrentDayCountriesStatistics()

        val cases = formatNumber(allCountries?.sumBy { it.cases ?: 0 } ?: 0)
        val recovered = formatNumber(allCountries?.sumBy { it.recovered ?: 0 } ?: 0)
        val deaths = formatNumber(allCountries?.sumBy { it.deaths ?: 0 } ?: 0)

        val myCountryIso = CountryCodeHelper.getDeviceCountryCode(getAppContext())
        val myCountryIsoUpper = myCountryIso?.toUpperCase(Locale.getDefault())
        val myCountryName =
            allCountries?.find { it.countryInfoDto?.iso2 == myCountryIsoUpper }?.country ?: ""

        // Move my country to top
        val myCountryStatistics = countriesStatistics.find { it.name == myCountryName }
        myCountryStatistics?.let {
            countriesStatistics.remove(it)
            countriesStatistics.add(0, it)
        }

        // Add worldwide statistics
        val worldwideStatistics = CountryStatisticsDvo(null, "Worldwide", cases, recovered, deaths)
        countriesStatistics.add(0, worldwideStatistics)

        return countriesStatistics
    }

    private fun hasCountriesStatisticsData() =
        historicalData.isNullOrEmpty() || allCountries.isNullOrEmpty()

    private fun getCurrentDayCountriesStatistics(): MutableList<CountryStatisticsDvo> {
        return allCountries?.map { buildCountryStatisticDvo(it) }?.toMutableList()
            ?: mutableListOf()
    }

    fun isCurrentDay(calendar: Calendar): Boolean {
        return calendar[Calendar.YEAR] == Calendar.getInstance()[Calendar.YEAR]
                && calendar[Calendar.MONTH] == Calendar.getInstance()[Calendar.MONTH]
                && calendar[Calendar.DAY_OF_MONTH] == Calendar.getInstance()[Calendar.DAY_OF_MONTH]
    }

    fun getWorldWideStatistics(calendar: Calendar): HashMap<String, Int> {
        return if (isCurrentDay(calendar)) {
            getTodayWorldWideStatistics()
        } else {
            getHistoricalWorldWideStatistics(calendar)
        }
    }

    private fun getTodayWorldWideStatistics(): HashMap<String, Int> {
        val worldwideCases = allCountries?.sumBy { it.cases ?: 0 } ?: 0
        val worldwideActive = allCountries?.sumBy { it.active ?: 0 } ?: 0
        val worldwideRecovered = allCountries?.sumBy { it.recovered ?: 0 } ?: 0
        val worldwideDeaths = allCountries?.sumBy { it.deaths ?: 0 } ?: 0

        val hasMap = mutableMapOf<String, Int>()

        hasMap["cases"] = 100
        hasMap["active"] = worldwideActive * 100 / worldwideCases
        hasMap["recovered"] = worldwideRecovered * 100 / worldwideCases
        hasMap["deaths"] = worldwideDeaths * 100 / worldwideCases

        return hasMap as HashMap<String, Int>
    }

    private fun getHistoricalWorldWideStatistics(calendar: Calendar): HashMap<String, Int> {
        val day = formatCalendar(calendar)

        val worldwideConfirmed = historicalData?.sumBy { it.timeline?.casesDto?.get(day) ?: 0 } ?: 0
        val worldwideRecovered =
            historicalData?.sumBy { it.timeline?.recovered?.get(day) ?: 0 } ?: 0
        val worldwideDeaths = historicalData?.sumBy { it.timeline?.deaths?.get(day) ?: 0 } ?: 0
        val worldwideActive = worldwideConfirmed - worldwideRecovered - worldwideDeaths

        val hasMap = mutableMapOf<String, Int>()

        hasMap["cases"] = 100
        hasMap["active"] = worldwideActive * 100 / worldwideConfirmed
        hasMap["recovered"] = worldwideRecovered * 100 / worldwideConfirmed
        hasMap["deaths"] = worldwideDeaths * 100 / worldwideConfirmed

        return hasMap as HashMap<String, Int>
    }

    private fun buildWorldwideStatistics(day: String): CountryStatisticsDvo {
        val worldwideConfirmed = historicalData?.sumBy { it.timeline?.casesDto?.get(day) ?: 0 } ?: 0
        val worldwideRecovered =
            historicalData?.sumBy { it.timeline?.recovered?.get(day) ?: 0 } ?: 0
        val worldwideDeaths = historicalData?.sumBy { it.timeline?.deaths?.get(day) ?: 0 } ?: 0

        return CountryStatisticsDvo(
            null,
            getResources().getString(R.string.statistics_worldwide),
            formatNumber(worldwideConfirmed),
            formatNumber(worldwideRecovered),
            formatNumber(worldwideDeaths)
        )
    }

    private fun buildCountryStatisticDvo(
        countryTimelineDto: CountryTimelineDto,
        countryDto: CountryDto,
        day: String
    ): CountryStatisticsDvo {
        return CountryStatisticsDvo(
            countryDto.countryInfoDto?.flag,
            countryTimelineDto.country ?: "",
            formatNumber(countryTimelineDto.timeline?.casesDto?.get(day) ?: 0),
            formatNumber(countryTimelineDto.timeline?.recovered?.get(day) ?: 0),
            formatNumber(countryTimelineDto.timeline?.deaths?.get(day) ?: 0)
        )
    }

    private fun buildCountryStatisticDvo(
        countryDto: CountryDto
    ): CountryStatisticsDvo {
        return CountryStatisticsDvo(
            countryDto.countryInfoDto?.flag,
            countryDto.country ?: "",
            formatNumber(countryDto.cases ?: 0),
            formatNumber(countryDto.active ?: 0),
            formatNumber(countryDto.deaths ?: 0)
        )
    }

    @SuppressLint("SimpleDateFormat")
    private fun formatCalendar(calendar: Calendar): String {
        val sdf = SimpleDateFormat("M/d/yy")
        return sdf.format(calendar.time)
    }

    private fun formatNumber(number: Int): String {
        val symbols = DecimalFormatSymbols()
        return when {
            number >= ONE_MILLION -> {
                symbols.decimalSeparator = ','
                val myFormatter = DecimalFormat("###.##", symbols)
                myFormatter.format(number * 1f / ONE_MILLION) + " M"
            }
            number >= ONE_HUNDRED_THOUSAND -> {
                symbols.decimalSeparator = ','
                val myFormatter = DecimalFormat("###.##", symbols)
                myFormatter.format(number * 1f / ONE_THOUSAND) + " K"
            }
            else -> {
                symbols.groupingSeparator = ','
                val myFormatter = DecimalFormat("###,###", symbols)
                myFormatter.format(number)
            }
        } ?: ""
    }

    companion object {
        const val ONE_MILLION = 1_000_000
        const val ONE_HUNDRED_THOUSAND = 100_000
        const val ONE_THOUSAND = 1_000
    }
}