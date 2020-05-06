package com.sergeykulyk.corona_statistics.ui.dashboard.preventation.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.sergeykulyk.corona_statistics.R
import com.sergeykulyk.corona_statistics.ui.dashboard.preventation.Preventation
import kotlinx.android.synthetic.main.fragment_dont.*

class DontFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dont, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dontViewPager.apply {
            adapter = buildAdapter()
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }
        dotsIndicator.setViewPager2(dontViewPager)

        val zoomOutPageTransformer = ZoomOutPageTransformer()
        dontViewPager.setPageTransformer { page, position ->
            zoomOutPageTransformer.transformPage(page, position)
        }
    }

    private fun buildAdapter() = PreventationAdapter(getSlides())

    private fun getSlides(): List<Preventation> {
        val wearMask = Preventation(
            R.drawable.ic_do_wear_mask,
            R.string.preventation_title_wear_mask,
            R.string.preventation_description_wear_mask,
            ""
        )

        return listOf(wearMask, wearMask, wearMask)
    }

}
