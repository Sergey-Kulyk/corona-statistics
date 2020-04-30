package com.sergeykulyk.corona_statistics.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.ui.dashboard.DashboardPageAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dashboardViewPager.adapter = DashboardPageAdapter(supportFragmentManager)
    }
}
