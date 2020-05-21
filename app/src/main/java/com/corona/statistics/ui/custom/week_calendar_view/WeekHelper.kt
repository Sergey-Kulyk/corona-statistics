package com.corona.statistics.ui.custom.week_calendar_view

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.*

class WeekHelper {

    @SuppressLint("SimpleDateFormat")
    fun getWeeksOfMonth(month: Int, year: Int): MutableList<Week> {
        val sdf = SimpleDateFormat("EEE dd")
        val cal = getInstance()

        cal[YEAR] = year
        cal[DAY_OF_MONTH] = 1
        cal[MONTH] = month

        var daysSize = cal.getActualMaximum(DAY_OF_YEAR)

        val firstDayOfWeek = getInstance().firstDayOfWeek
        while (cal[DAY_OF_WEEK] != firstDayOfWeek) {
            cal.add(DAY_OF_MONTH, 1)
            daysSize--
        }

        val remainingDays = daysSize % 7
        if (remainingDays == 0) daysSize += 7 else daysSize = daysSize + 7 - remainingDays

        val weeks = mutableListOf<Week>()
        val weekDays = mutableListOf<WeekDay>()

        var currentDay: WeekDay? = null
        for (dayIndex in 1..daysSize) {

            val weekDayLetter = sdf.format(cal.time)
            val weekDay = WeekDay(cal.clone() as Calendar, weekDayLetter)
            weekDays.add(weekDay)

            if (weekDay.calendar[YEAR] == getInstance()[YEAR]
                && weekDay.calendar[MONTH] == getInstance()[MONTH]
                && weekDay.calendar[DAY_OF_MONTH] == getInstance()[DAY_OF_MONTH]
            ) {
                currentDay = weekDay
            }
//            println(weekDayLetter)

            if (dayIndex % 7 == 0) {
                weeks.add(Week(weekDays.toList(), getMonth(weekDays), currentDay))
                weekDays.clear()
                currentDay = null
            }
            cal.add(DATE, 1)
        }

        return weeks
    }

    private fun getMonth(weekDays: List<WeekDay>): Int {
        val firstDayMonth = weekDays.first().calendar.get(MONTH)
        val lastDayMonth = weekDays.last().calendar.get(MONTH)

        val map = weekDays.map { it.calendar.get(MONTH) }
        val month1Count = map.count { it == firstDayMonth }

        return if (month1Count > 3) {
            firstDayMonth
        } else {
            lastDayMonth
        }
    }

    fun getWeeks(): List<Week> {
        return getWeeksOfMonth(1, 2020)
    }
}