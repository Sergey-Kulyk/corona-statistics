package com.sergeykulyk.corona_statistics.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.ext.gone
import com.sergeykulyk.corona_statistics.ext.show
import com.sergeykulyk.corona_statistics.ui.dashboard.DashboardPageAdapter
import com.sergeykulyk.corona_statistics.ui.dashboard.preventation.tabs.ZoomOutPageTransformer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_do.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = DashboardPageAdapter(supportFragmentManager)
        dashboardViewPager.adapter = adapter
        dashboardTabs.setupWithViewPager(dashboardViewPager)
        dashboardViewPager.offscreenPageLimit = 4

        val tabOverview = dashboardTabs.getTabAt(0)
        val tabSymptoms = dashboardTabs.getTabAt(1)
        val tabPrevention = dashboardTabs.getTabAt(2)
        val tabStatistics = dashboardTabs.getTabAt(3)

        tabOverview?.setCustomView(R.layout.tab_layout_custom_view)
        tabSymptoms?.setCustomView(R.layout.tab_layout_custom_view)
        tabPrevention?.setCustomView(R.layout.tab_layout_custom_view)
        tabStatistics?.setCustomView(R.layout.tab_layout_custom_view)

        tabOverview?.setIcon(R.drawable.ic_tab_overview)
        tabSymptoms?.setIcon(R.drawable.ic_tab_symptoms)
        tabPrevention?.setIcon(R.drawable.ic_tab_preventions)
        tabStatistics?.setIcon(R.drawable.ic_tab_statistics)

        tabSymptoms?.view?.findViewById<ImageView>(android.R.id.icon)?.gone()
        tabPrevention?.view?.findViewById<ImageView>(android.R.id.icon)?.gone()
        tabStatistics?.view?.findViewById<ImageView>(android.R.id.icon)?.gone()

        // Add rotation for overview icon programmatically for better ui style.
        tabOverview?.view?.findViewById<ImageView>(android.R.id.icon)?.rotation = 45f

        dashboardTabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val tabViewChild = tab.view.findViewById<TextView>(android.R.id.text1)
                val tabIcon = tab.view.findViewById<ImageView>(android.R.id.icon)
                tabIcon.show()
                tabViewChild.typeface =
                    ResourcesCompat.getFont(baseContext, R.font.ibm_plex_sans_bold)
                tabViewChild.textSize = 15f
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                val tabViewChild = tab.view.findViewById<TextView>(android.R.id.text1)
                val tabIcon = tab.view.findViewById<ImageView>(android.R.id.icon)
                tabIcon.show()
                tabViewChild.typeface =
                    ResourcesCompat.getFont(baseContext, R.font.ibm_plex_sans_bold)
                tabViewChild.textSize = 15f
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                val tabViewChild = tab.view.findViewById<TextView>(android.R.id.text1)
                val tabIcon = tab.view.findViewById<ImageView>(android.R.id.icon)
                tabIcon.gone()
                tabViewChild.typeface =
                    ResourcesCompat.getFont(baseContext, R.font.ibm_plex_sans_medium)
                tabViewChild.textSize = 14f
            }
        })
        dashboardViewPager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 3) {
                    adapter.statisticsFragment.startAnimation()
                }
            }
        })
    }
}

