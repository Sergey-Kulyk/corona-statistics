package com.sergeykulyk.corona_statistics.ui.custom.week_calendar_view

import java.util.*


data class Week(
    val days: List<WeekDay> = listOf(),
    val month: Int = 1,
    var selectedDay: WeekDay?
) {

    fun hasSelectedDay() = selectedDay != null

    fun isCurrentWeek(): Boolean {
        return days.find {
            it.calendar[Calendar.YEAR] == Calendar.getInstance()[Calendar.YEAR]
                    && it.calendar[Calendar.MONTH] == Calendar.getInstance()[Calendar.MONTH]
                    && it.calendar[Calendar.DAY_OF_MONTH] == Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        } != null
    }

    fun getCurrentDay(): WeekDay? {
        return days.find {
            it.calendar[Calendar.YEAR] == Calendar.getInstance()[Calendar.YEAR]
                    && it.calendar[Calendar.MONTH] == Calendar.getInstance()[Calendar.MONTH]
                    && it.calendar[Calendar.DAY_OF_MONTH] == Calendar.getInstance()[Calendar.DAY_OF_MONTH]
        }
    }

    fun getSelectedDayIndex(): Int? {
        days.forEachIndexed { i, weekDay ->
            if (
                selectedDay != null
                && weekDay.calendar[Calendar.YEAR] == selectedDay!!.calendar[Calendar.YEAR]
                && weekDay.calendar[Calendar.MONTH] == selectedDay!!.calendar[Calendar.MONTH]
                && weekDay.calendar[Calendar.DAY_OF_MONTH] == selectedDay!!.calendar[Calendar.DAY_OF_MONTH]
            ) return i
        }
        return null
    }
}