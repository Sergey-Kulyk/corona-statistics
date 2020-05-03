package com.sergeykulyk.corona_statistics.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.getResources
import com.sergeykulyk.corona_statistics.ui.dashboard.overview.OverviewFragment
import com.sergeykulyk.corona_statistics.ui.dashboard.symptoms.SymptomsFragment

class DashboardPageAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return  when (position) {
            1 -> SymptomsFragment()
            2 -> SymptomsFragment()
            3 -> SymptomsFragment()
            else -> OverviewFragment()
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when (position) {
            1 -> R.string.tab_symptoms
            2 -> R.string.tab_prevention
            3 -> R.string.tab_statistics
            else -> R.string.tab_overview
        }
        return getResources().getString(title)
    }


}