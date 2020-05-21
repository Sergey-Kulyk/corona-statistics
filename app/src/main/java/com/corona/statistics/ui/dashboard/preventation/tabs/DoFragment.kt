package com.corona.statistics.ui.dashboard.preventation.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.corona.statistics.R
import kotlinx.android.synthetic.main.fragment_do.*

class DoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_do, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doViewPager.apply {
            doViewPager.adapter = buildAdapter()
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        dotsIndicator.setViewPager2(doViewPager)

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        doViewPager.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }
    }

    private fun buildAdapter(): PreventationAdapter {
        val slides = PreventationSlidesBuilder().buildDoSlides()
        return PreventationAdapter(slides)
    }

}
