package com.sergeykulyk.corona_statistics.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sergeykulyk.corona_statistics.ui.dashboard.overview.OverviewFragment

class DashboardPageAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return OverviewFragment()

    }

    override fun getCount(): Int {
        return 1
    }
}