package com.sergeykulyk.corona_statistics.ui.dashboard.overview

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
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
        viewModel = ViewModelProviders.of(this).get(OverviewViewModel::class.java)

        viewModel.statisticsLiveData.observe(this, Observer {
            val adapter = OverviewStatisticsAdapter(it)
            statisticsRecyclerView.layoutManager?.isAutoMeasureEnabled = true
            statisticsRecyclerView.adapter = adapter

            val decoration = HorizontalSpaceItemDecoration(
                resources.getDimension(R.dimen.overview_statistics_space).toInt()
            )
            statisticsRecyclerView.addItemDecoration(decoration)
        })

        viewModel.getStatistics()

        val objectAnimator = ObjectAnimator.ofFloat(virus, "rotation", 0f, 360f)
        objectAnimator.startDelay = 1000
        objectAnimator.repeatCount = ObjectAnimator.INFINITE
        objectAnimator.duration = 22 * 1000
        objectAnimator.start()

        appBarLayout?.addOnOffsetChangedListener(this)
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
}
