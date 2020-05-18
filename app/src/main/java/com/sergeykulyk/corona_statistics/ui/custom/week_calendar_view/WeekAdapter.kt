package com.sergeykulyk.corona_statistics.ui.custom.week_calendar_view

import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.getResources
import kotlinx.android.synthetic.main.item_week.view.*
import java.util.*

class WeeksAdapter(val weeks: List<Week>) :
    RecyclerView.Adapter<WeeksAdapter.WeekViewHolder>() {

    var selectedWeekIndex = 0
    var selectedWeekDay: WeekDay? = null
    var selectedDayTitle: TextView? = null
    var weekView: View? = null

    var onWeekDaySelectedListener: OnWeekDaySelectedListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_week, parent, false)
        return WeekViewHolder(itemView)
    }

    override fun getItemCount() = weeks.size

    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        holder.bind(weeks[position])
    }

    inner class WeekViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(week: Week) {
            if (week.hasSelectedDay()) {
                val currentDayIndex = week.getSelectedDayIndex()
                currentDayIndex?.let {
                    selectedWeekDay = week.days[currentDayIndex]
                    when (currentDayIndex) {
                        0 -> {
                            updateSelectedDay(week.days[currentDayIndex])
                            setSelectedDayTitleColor(itemView.dayTitle1)
                            selectDay(itemView.dayLayout1)
                        }
                        1 -> {
                            updateSelectedDay(week.days[currentDayIndex])
                            setSelectedDayTitleColor(itemView.dayTitle2)
                            selectDay(itemView.dayLayout2)
                        }
                        2 -> {
                            updateSelectedDay(week.days[currentDayIndex])
                            setSelectedDayTitleColor(itemView.dayTitle3)
                            selectDay(itemView.dayLayout3)
                        }
                        3 -> {
                            updateSelectedDay(week.days[currentDayIndex])
                            setSelectedDayTitleColor(itemView.dayTitle4)
                            selectDay(itemView.dayLayout4)
                        }
                        4 -> {
                            updateSelectedDay(week.days[currentDayIndex])
                            setSelectedDayTitleColor(itemView.dayTitle5)
                            selectDay(itemView.dayLayout5)
                        }
                        5 -> {
                            updateSelectedDay(week.days[currentDayIndex])
                            setSelectedDayTitleColor(itemView.dayTitle6)
                            selectDay(itemView.dayLayout6)
                        }
                        6 -> {
                            updateSelectedDay(week.days[currentDayIndex])
                            setSelectedDayTitleColor(itemView.dayTitle7)
                            selectDay(itemView.dayLayout7)
                        }
                    }
                }
            } else {
                itemView.dayTitle1?.setTextColor(getResources().getColor(R.color.blueDark))
                itemView.dayTitle2?.setTextColor(getResources().getColor(R.color.blueDark))
                itemView.dayTitle3?.setTextColor(getResources().getColor(R.color.blueDark))
                itemView.dayTitle4?.setTextColor(getResources().getColor(R.color.blueDark))
                itemView.dayTitle5?.setTextColor(getResources().getColor(R.color.blueDark))
                itemView.dayTitle6?.setTextColor(getResources().getColor(R.color.blueDark))
                itemView.dayTitle7?.setTextColor(getResources().getColor(R.color.blueDark))

                unselect()
            }

            itemView.dayTitle1.text = week.days[0].getDayOfWeekLetter()
            itemView.dayTitle2.text = week.days[1].getDayOfWeekLetter()
            itemView.dayTitle3.text = week.days[2].getDayOfWeekLetter()
            itemView.dayTitle4.text = week.days[3].getDayOfWeekLetter()
            itemView.dayTitle5.text = week.days[4].getDayOfWeekLetter()
            itemView.dayTitle6.text = week.days[5].getDayOfWeekLetter()
            itemView.dayTitle7.text = week.days[6].getDayOfWeekLetter()

            itemView.day1.text = week.days[0].calendar.get(Calendar.DAY_OF_MONTH).toString()
            itemView.day2.text = week.days[1].calendar.get(Calendar.DAY_OF_MONTH).toString()
            itemView.day3.text = week.days[2].calendar.get(Calendar.DAY_OF_MONTH).toString()
            itemView.day4.text = week.days[3].calendar.get(Calendar.DAY_OF_MONTH).toString()
            itemView.day5.text = week.days[4].calendar.get(Calendar.DAY_OF_MONTH).toString()
            itemView.day6.text = week.days[5].calendar.get(Calendar.DAY_OF_MONTH).toString()
            itemView.day7.text = week.days[6].calendar.get(Calendar.DAY_OF_MONTH).toString()

            itemView.dayLayout1.setOnClickListener {
                val day = week.days[0]
                unselect1()
                selectedWeekDay = day
                updateSelectedDay(day)
                setSelectedDayTitleColor(itemView.dayTitle1)
                selectDay(it)
                onWeekDaySelectedListener?.onWeekDaySelected(day)
            }
            itemView.dayLayout2.setOnClickListener {
                val day = week.days[1]
                unselect1()
                selectedWeekDay = day
                updateSelectedDay(day)
                setSelectedDayTitleColor(itemView.dayTitle2)
                selectDay(it)
                onWeekDaySelectedListener?.onWeekDaySelected(day)
            }
            itemView.dayLayout3.setOnClickListener {
                val day = week.days[2]
                unselect1()
                selectedWeekDay = day
                updateSelectedDay(day)
                setSelectedDayTitleColor(itemView.dayTitle3)
                selectDay(it)
                onWeekDaySelectedListener?.onWeekDaySelected(day)
            }
            itemView.dayLayout4.setOnClickListener {
                val day = week.days[3]
                unselect1()
                selectedWeekDay = day
                updateSelectedDay(day)
                setSelectedDayTitleColor(itemView.dayTitle4)
                selectDay(it)
                onWeekDaySelectedListener?.onWeekDaySelected(day)
            }
            itemView.dayLayout5.setOnClickListener {
                val day = week.days[4]
                unselect1()
                selectedWeekDay = day
                updateSelectedDay(day)
                setSelectedDayTitleColor(itemView.dayTitle5)
                selectDay(it)
                onWeekDaySelectedListener?.onWeekDaySelected(day)
            }
            itemView.dayLayout6.setOnClickListener {
                val day = week.days[5]
                unselect1()
                selectedWeekDay = day
                updateSelectedDay(day)
                setSelectedDayTitleColor(itemView.dayTitle6)
                selectDay(it)
                onWeekDaySelectedListener?.onWeekDaySelected(day)
            }
            itemView.dayLayout7.setOnClickListener {
                val day = week.days[6]
                unselect1()
                selectedWeekDay = day
                updateSelectedDay(day)
                setSelectedDayTitleColor(itemView.dayTitle7)
                selectDay(it)
                onWeekDaySelectedListener?.onWeekDaySelected(day)
            }
        }

        private fun isAfter(day: WeekDay) =
            selectedWeekDay?.calendar?.after(day.calendar) ?: false

        private fun updateSelectedDay(
            weekDay: WeekDay
        ) {
            weeks[selectedWeekIndex].selectedDay = null
            selectedWeekIndex = adapterPosition
            weeks[selectedWeekIndex].selectedDay = weekDay
        }

        private fun setSelectedDayTitleColor(dayTitleTextView: TextView) {
            selectedDayTitle?.setTextColor(getResources().getColor(R.color.blueDark))
            selectedDayTitle = dayTitleTextView
            dayTitleTextView.setTextColor(getResources().getColor(android.R.color.white))
        }

        private fun selectDay(it: View) {
            TransitionManager.beginDelayedTransition(itemView.root)

            weekView = itemView

            val constraintSet = ConstraintSet()
            constraintSet.clone(itemView.root)
            constraintSet.connect(R.id.selector, ConstraintSet.START, it.id, ConstraintSet.START, 0)
            constraintSet.connect(R.id.selector, ConstraintSet.END, it.id, ConstraintSet.END, 0)
            constraintSet.connect(R.id.selector, ConstraintSet.TOP, it.id, ConstraintSet.TOP, 0)
            constraintSet.connect(
                R.id.selector,
                ConstraintSet.BOTTOM,
                it.id,
                ConstraintSet.BOTTOM,
                0
            )
            constraintSet.applyTo(itemView.root)
        }

        private fun unselect() {
            itemView.let {
                val constraintSet = ConstraintSet()
                constraintSet.clone(it.root)

                //                if (start) {

                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.START,
                    1,
                    ConstraintSet.START,
                    0
                )
                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START,
                    100
                )

                //                } else {
                //                    val layoutParams = ConstraintLayout.LayoutParams(0, 0)
                //                    layoutParams.setMargins(100, 0, 0, 0)
                //                    it.selector.layoutParams = layoutParams
                //
                //                    constraintSet.connect(
                //                        R.id.selector,
                //                        ConstraintSet.START,
                //                        ConstraintSet.PARENT_ID,
                //                        ConstraintSet.END,
                //                        0
                //                    )
                //                    constraintSet.connect(
                //                        R.id.selector,
                //                        ConstraintSet.END,
                //                        1,
                //                        ConstraintSet.END,
                //                        0
                //                    )
                //                }

                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP,
                    0
                )
                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM,
                    0
                )
                constraintSet.applyTo(it.root)
            }
        }

        private fun unselect1() {
            weekView?.let {
                val constraintSet = ConstraintSet()
                constraintSet.clone(it.root)

                //                if (start) {

                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.START,
                    1,
                    ConstraintSet.START,
                    0
                )
                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START,
                    100
                )

                //                } else {
                //                    val layoutParams = ConstraintLayout.LayoutParams(0, 0)
                //                    layoutParams.setMargins(100, 0, 0, 0)
                //                    it.selector.layoutParams = layoutParams
                //
                //                    constraintSet.connect(
                //                        R.id.selector,
                //                        ConstraintSet.START,
                //                        ConstraintSet.PARENT_ID,
                //                        ConstraintSet.END,
                //                        0
                //                    )
                //                    constraintSet.connect(
                //                        R.id.selector,
                //                        ConstraintSet.END,
                //                        1,
                //                        ConstraintSet.END,
                //                        0
                //                    )
                //                }

                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP,
                    0
                )
                constraintSet.connect(
                    R.id.selector,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM,
                    0
                )
                constraintSet.applyTo(it.root)
            }
        }
    }

    interface OnWeekDaySelectedListener {
        fun onWeekDaySelected(weekDay: WeekDay)
    }
}