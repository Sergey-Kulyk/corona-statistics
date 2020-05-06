package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.florent37.viewanimator.ViewAnimator
import com.google.android.material.appbar.AppBarLayout
import com.sergeykulyk.corona_statistics.R
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlin.math.abs


class OverviewFragment : Fragment(), AppBarLayout.OnOffsetChangedListener {

    private lateinit var viewModel: OverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        appBarLayout.addOnOffsetChangedListener(this)
        virus.setOnClickListener { startVirusRubberAnimation(it) }

        val adapter = OverviewStatisticsAdapter()
        statisticsRecyclerView.layoutManager?.isAutoMeasureEnabled = true
        statisticsRecyclerView.adapter = adapter

        val decoration = HorizontalSpaceItemDecoration(
            resources.getDimension(R.dimen.overview_statistics_space).toInt()
        )
        statisticsRecyclerView.addItemDecoration(decoration)

        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        viewModel.statisticsLiveData.observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        viewModel.mapCountriesLiveData.observe(this, Observer {
            mapStatisticsView.continents = it
            mapStatisticsView.invalid()
        })

        startScreenTransitionsAnimations()
        startVirusRotationAnimation()

        viewModel.getStatistics()
        updateData()
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout, offset: Int) {
        val maxScroll: Int = appBarLayout.totalScrollRange
        val percentage = abs(offset).toFloat() / maxScroll.toFloat()

        if (percentage < 0.7) {
            virus.animate().alpha(0.7f).duration = 1000
        } else {
            virus.animate().alpha(0.3f).duration = 1000
        }
    }

    private fun startScreenTransitionsAnimations() {
        setViewsZeroAlpha()

        val logoAnimation = buildTransitionUpAnimation(logo)
        val virusAnimation = buildTransitionUpAnimation(virus)
        val mapAnimation = buildTransitionUpAnimation(mapStatisticsView)
        val statisticsAnimation = buildTransitionUpAnimation(statisticsRecyclerView)

        val logoAlphaAnimation = ObjectAnimator.ofFloat(logo, "alpha", 1f)
        val virusAlphaAnimation = ObjectAnimator.ofFloat(virus, "alpha", 0.7f)
        val mapAlphaAnimation = ObjectAnimator.ofFloat(mapStatisticsView, "alpha", 1f)
        val statisticsAlphaAnimation = ObjectAnimator.ofFloat(statisticsRecyclerView, "alpha", 1f)

        virusAnimation.startDelay = 150
        virusAlphaAnimation.startDelay = 150

        mapAnimation.startDelay = 300
        mapAlphaAnimation.startDelay = 300

        mapAnimation.duration = TRANSITION_ANIMATION_DELAY
        mapAlphaAnimation.duration = TRANSITION_ANIMATION_DELAY

        statisticsAnimation.startDelay = 450
        statisticsAlphaAnimation.startDelay = 450

        val set = AnimatorSet()

        set.play(logoAnimation)
            .with(logoAlphaAnimation)
            .with(virusAnimation)
            .with(virusAlphaAnimation)
            .with(mapAnimation)
            .with(mapAlphaAnimation)
            .with(statisticsAnimation)
            .with(statisticsAlphaAnimation)

        set.duration = TRANSITION_ANIMATION_DELAY
        set.interpolator = AccelerateDecelerateInterpolator()

        set.start()
    }

    private fun setViewsZeroAlpha() {
        logo.alpha = 0f
        virus.alpha = 0f
        mapStatisticsView.alpha = 0f
        statisticsRecyclerView.alpha = 0f
    }

    private fun buildTransitionUpAnimation(view: View) =
        ObjectAnimator.ofFloat(view, "translationY", 150f, 0f)

    private fun startVirusRotationAnimation() {
        val objectAnimator = ObjectAnimator.ofFloat(virus, "rotation", 0f, 360f)
        objectAnimator.repeatCount = ObjectAnimator.INFINITE
        objectAnimator.startDelay = VIRUS_ANIMATION_START_DELAY
        objectAnimator.duration = VIRUS_ANIMATION_DELAY
        objectAnimator.start()
    }

    private fun startVirusRubberAnimation(it: View?) {
        ViewAnimator.animate(it).rubber().duration(700).start()
    }

    private fun updateData() {
        Handler().postDelayed({
            viewModel.getStatistics()
            updateData()
        }, DATA_UPDATE_DELAY)
    }

    companion object {
        private const val TRANSITION_ANIMATION_DELAY = 600L
        private const val VIRUS_ANIMATION_START_DELAY = 1000L
        private const val VIRUS_ANIMATION_DELAY = 22 * 1000L
        private const val DATA_UPDATE_DELAY = 1 * 10 * 1000L
    }
}
