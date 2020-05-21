package com.corona.statistics.ui.custom.week_calendar_view

import java.util.Calendar

data class WeekDay(
    val calendar: Calendar,
    val dayOfWeek: String
) {
    fun getDayOfWeekLetter() = dayOfWeek.first().toString()
}