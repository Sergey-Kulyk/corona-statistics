package com.sergeykulyk.corona_statistics.ui.custom.week_calendar_view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout
import androidx.viewpager2.widget.ViewPager2
import java.util.*

class WeekCalendarView(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    private var weeksViewPager: ViewPager2 = ViewPager2(context)
    private var weeksAdapter: WeeksAdapter
    var onDaySelectedListener: OnDaySelectedListener? = null
    var onMonthChangedListener: OnMonthChangedListener? = null

    init {
        val weeks = WeekHelper().getWeeks()
        weeksViewPager.layoutParams =
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)

        weeksAdapter = WeeksAdapter(weeks)
        weeksAdapter.onWeekDaySelectedListener =
            object : WeeksAdapter.OnWeekDaySelectedListener {
                override fun onWeekDaySelected(weekDay: WeekDay) {
                    onDaySelectedListener?.onDaySelected(weekDay.calendar)
                }
            }
        weeksViewPager.adapter = weeksAdapter
        addView(weeksViewPager)
        weeksViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                onMonthChangedListener?.onMonthChanged(theMonth(weeks[position].month) ?: "")
            }
        })
    }

    fun theMonth(month: Int): String? {
        val monthNames = arrayOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
        return monthNames[month]
    }

    fun selectCurrentDay() {
        val selectedWeekIndex = weeksAdapter.weeks.indexOfLast { it.hasSelectedDay() }
        weeksViewPager.currentItem = selectedWeekIndex
        val currentWeekDay = weeksAdapter.weeks[selectedWeekIndex].getCurrentDay()
        Log.d("CurrentWeekDay", currentWeekDay?.dayOfWeek + "")
        currentWeekDay?.calendar?.let { onDaySelectedListener?.onDaySelected(it) }
    }

}

interface OnDaySelectedListener {
    fun onDaySelected(calendar: Calendar)
}

interface OnMonthChangedListener {
    fun onMonthChanged(monthName: String)
}

