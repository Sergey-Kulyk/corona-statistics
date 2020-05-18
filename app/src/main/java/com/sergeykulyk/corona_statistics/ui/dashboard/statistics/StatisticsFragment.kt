package com.sergeykulyk.corona_statistics.ui.dashboard.statistics

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.florent37.viewanimator.ViewAnimator
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.ui.custom.week_calendar_view.OnDaySelectedListener
import com.sergeykulyk.corona_statistics.ui.custom.week_calendar_view.OnMonthChangedListener
import com.sergeykulyk.corona_statistics.ui.dashboard.overview.OverviewFragment
import kotlinx.android.synthetic.main.content_place_holder.*
import kotlinx.android.synthetic.main.content_statictics_percent.*
import kotlinx.android.synthetic.main.fragment_statistics.*
import java.util.*

class StatisticsFragment : Fragment() {

    private lateinit var viewModel: StatisticsViewModel
    private val adapter by lazy {
        return@lazy CountriesAdapter()
    }

    private var isAnimationsShown = false

    private var lastCasesPercent = 0
    private var lastActivePercent = 0
    private var lastRecoveredPercent = 0
    private var lastDeathsPercent = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(StatisticsViewModel::class.java)

        countriesRecyclerView.layoutManager = LinearLayoutManager(context)
        countriesRecyclerView.adapter = adapter

        viewModel.countriesStatisticsLiveData.observe(this, androidx.lifecycle.Observer {
            countriesRecyclerView.smoothScrollToPosition(0)
            adapter.countries = it
            adapter.notifyDataSetChanged()
        })
        viewModel.dayWorldwideStatisticsLiveData.observe(this, androidx.lifecycle.Observer {
            if (isAnimationsShown) {
                startPercentHeightAnimation(it)
            }
        })
        viewModel.isPlaceHolderShowingLiveData.observe(this, androidx.lifecycle.Observer {
            adapter.countries = listOf()
            adapter.notifyDataSetChanged()

            if (it) {
                placeHolder.visibility = View.VISIBLE
            } else {
                placeHolder.visibility = View.GONE
            }
        })

        viewModel.isPlaceHolderShowingLiveData.observe(this, androidx.lifecycle.Observer { })
        viewModel.getCountryStatistics()

        weekCalendarView.onDaySelectedListener = object : OnDaySelectedListener {
            override fun onDaySelected(calendar: Calendar) {
                viewModel.getHistoricalForDay(calendar)
            }
        }
        weekCalendarView.onMonthChangedListener = object : OnMonthChangedListener {
            override fun onMonthChanged(name: String) {
                monthName.text = name
            }
        }
        view.postDelayed({
            weekCalendarView.selectCurrentDay()
        }, 2000)


        // Set alpha for view for animation
        setViewsZeroAlpha()

        placeHolderIcon.setOnClickListener {
            ViewAnimator.animate(it).rubber().duration(700).start()
        }
    }

    fun startAnimation() {
        if (!isAnimationsShown) {
            startScreenTransitionsAnimations()
        }
    }

    private fun startPercentHeightAnimation(it: HashMap<String, Int>) {
        val defaultHeight =
            resources.getDimension(R.dimen.statistics_percents_block_height_default)
        val percentBlockHeight =
            resources.getDimension(R.dimen.statistics_percents_block_height) - 90 - defaultHeight

        val casesPercentValue = it["cases"] ?: 0
        val activePercentValue = it["active"] ?: 0
        val recoveredPercentValue = it["recovered"] ?: 0
        val deathsPercentValue = it["deaths"] ?: 0

        val casesPercentHeight =
            calculateHeight(
                defaultHeight,
                percentBlockHeight,
                casesPercentValue
            )
        val activePercentHeight =
            calculateHeight(
                defaultHeight,
                percentBlockHeight,
                activePercentValue
            )
        val recoveredPercentHeight =
            calculateHeight(
                defaultHeight,
                percentBlockHeight,
                recoveredPercentValue
            )
        val deathsPercentHeight =
            calculateHeight(
                defaultHeight,
                percentBlockHeight,
                deathsPercentValue
            )

        startAnimationHeight(
            casesPercentLayout,
            casesPercentLayout.height.toFloat(),
            casesPercentHeight
        )
        startAnimationHeight(
            activePercentLayout,
            activePercentLayout.height.toFloat(),
            activePercentHeight
        )
        startAnimationHeight(
            recoveredPercentLayout,
            recoveredPercentLayout.height.toFloat(),
            recoveredPercentHeight
        )
        startAnimationHeight(
            deathsPercentLayout,
            deathsPercentLayout.height.toFloat(),
            deathsPercentHeight
        )

        startCountAnimation(casesPercent, lastCasesPercent, casesPercentValue)
        startCountAnimation(activePercent, lastActivePercent, activePercentValue)
        startCountAnimation(recoveredPercent, lastRecoveredPercent, recoveredPercentValue)
        startCountAnimation(deathsPercent, lastDeathsPercent, deathsPercentValue)

        lastCasesPercent = casesPercentValue
        lastActivePercent = activePercentValue
        lastRecoveredPercent = recoveredPercentValue
        lastDeathsPercent = deathsPercentValue
    }


    private fun calculateHeight(defaultHeight: Float, percentBlockHeight: Float, percent: Int) =
        percentBlockHeight * percent / 100 + defaultHeight

    private fun startAnimationHeight(
        view: View,
        defaultHeight: Float,
        percentBlockHeight: Float
    ) {
        val animatiobDelay = 700L

        ViewAnimator.animate(view)
            .height(defaultHeight, percentBlockHeight)
            .duration(animatiobDelay)
            .start()
    }

    private fun startCountAnimation(textView: TextView, percentStart: Int, percentEnd: Int) {
        val animator = ValueAnimator.ofInt(percentStart, percentEnd)
        animator.duration = 700L
        animator.addUpdateListener { percent -> textView.text = "${percent.animatedValue}%" }
        animator.start()
    }

    private fun startScreenTransitionsAnimations() {
        setViewsZeroAlpha()

        val titleAnimation = buildTransitionUpAnimation(statisticsTitle)
        val weekCalendarAnimation = buildTransitionUpAnimation(weekCalendarView)
        val percentsAnimation = buildTransitionUpAnimation(percentsStatisticsLayout)
        val countriesDescriptionAnimation =
            buildTransitionUpAnimation(countryValueDescriptionLayout)
        val countriesAnimation = buildTransitionUpAnimation(countriesRecyclerView)

        val titleAlphaAnimation = ObjectAnimator.ofFloat(statisticsTitle, "alpha", 1f)
        val weekCalendarAlphaAnimation = ObjectAnimator.ofFloat(weekCalendarView, "alpha", 0.7f)
        val percentsAlphaAnimation = ObjectAnimator.ofFloat(percentsStatisticsLayout, "alpha", 1f)
        percentsAlphaAnimation.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
                viewModel.dayWorldwideStatisticsLiveData.value?.let { startPercentHeightAnimation(it) }
            }
        })

        val countriesDescriptionAlphaAnimation =
            ObjectAnimator.ofFloat(countryValueDescriptionLayout, "alpha", 1f)
        val countriesAlphaAnimation =
            ObjectAnimator.ofFloat(countriesRecyclerView, "alpha", 1f)

        weekCalendarAnimation.startDelay = 150
        weekCalendarAlphaAnimation.startDelay = 150

        percentsAnimation.startDelay = 300
        percentsAlphaAnimation.startDelay = 300

        percentsAnimation.duration = OverviewFragment.TRANSITION_ANIMATION_DELAY
        percentsAlphaAnimation.duration = OverviewFragment.TRANSITION_ANIMATION_DELAY

        countriesDescriptionAnimation.startDelay = 450
        countriesDescriptionAlphaAnimation.startDelay = 450

        countriesAnimation.startDelay = 450
        countriesAlphaAnimation.startDelay = 450

        val set = AnimatorSet()

        set.play(titleAnimation)
            .with(titleAlphaAnimation)
            .with(weekCalendarAnimation)
            .with(weekCalendarAlphaAnimation)
            .with(percentsAnimation)
            .with(percentsAlphaAnimation)
            .with(countriesAnimation)
            .with(countriesAlphaAnimation)
            .with(countriesDescriptionAnimation)
            .with(countriesDescriptionAlphaAnimation)

        set.duration = OverviewFragment.TRANSITION_ANIMATION_DELAY
        set.interpolator = AccelerateDecelerateInterpolator()
        set.start()

        if (!isAnimationsShown) {
//            isAnimationsShown = true
        }
    }

    private fun setViewsZeroAlpha() {
        statisticsTitle.alpha = 0f
        weekCalendarView.alpha = 0f
        percentsStatisticsLayout.alpha = 0f
        countryValueDescriptionLayout.alpha = 0f
        countriesRecyclerView.alpha = 0f
    }

    private fun buildTransitionUpAnimation(view: View) =
        ObjectAnimator.ofFloat(view, "translationY", 150f, 0f)

}
