package com.sergeykulyk.corona_statistics.ui.dashboard.preventation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.getResources
import com.sergeykulyk.corona_statistics.ui.dashboard.preventation.tabs.DoFragment
import com.sergeykulyk.corona_statistics.ui.dashboard.preventation.tabs.DontFragment

class PreventationPagerAdapter(fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val doFragment = DoFragment()
    private val dontFragment = DontFragment()

    override fun getItem(position: Int): Fragment {
       return if (position == 0) {
           doFragment
       } else {
           dontFragment
       }
    }

    override fun getCount() = 2

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) {
            getResources().getString(R.string.preventation_tab_do)
        } else {
            getResources().getString(R.string.preventation_tab_dont)
        }
    }
}