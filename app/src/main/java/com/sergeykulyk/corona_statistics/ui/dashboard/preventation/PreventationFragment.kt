package com.sergeykulyk.corona_statistics.ui.dashboard.preventation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.map_statistics.vectorchildfinder.VectorChildFinder
import kotlinx.android.synthetic.main.fragment_preventation.*

class PreventationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_preventation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PreventationPagerAdapter(fragmentManager!!)
        preventationViewPager.adapter = adapter

        doTabButton.setOnClickListener {
            it.isSelected = true
            dontTabButton.isSelected = false
            preventationViewPager.currentItem = 0
        }
        dontTabButton.setOnClickListener {
            it.isSelected = true
            doTabButton.isSelected = false
            preventationViewPager.currentItem = 1
        }

    }
}
