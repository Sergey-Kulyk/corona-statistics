package com.corona.statistics.ui.dashboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.corona.statistics.R
import com.corona.statistics.getResources
import com.corona.statistics.ui.dashboard.overview.OverviewFragment
import com.corona.statistics.ui.dashboard.preventation.PreventationFragment
import com.corona.statistics.ui.dashboard.statistics.StatisticsFragment
import com.corona.statistics.ui.dashboard.symptoms.SymptomsFragment

class DashboardPageAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    val overviewFragment = OverviewFragment()
    val symptomsFragment = SymptomsFragment()
    val preventationFragment = PreventationFragment()
    val statisticsFragment = StatisticsFragment()

    override fun getItem(position: Int): Fragment {
        return  when (position) {
            1 -> symptomsFragment
            2 -> preventationFragment
            3 -> statisticsFragment
            else -> overviewFragment
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