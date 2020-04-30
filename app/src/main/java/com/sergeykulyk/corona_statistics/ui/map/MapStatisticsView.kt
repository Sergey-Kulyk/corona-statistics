package com.sergeykulyk.corona_statistics.ui.map

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageView
import com.sergeykulyk.corona_statistics.R

class MapStatisticsView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    init {
        val imageView = ImageView(context)
        imageView.setImageResource(R.drawable.im_map_statistics)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        addView(imageView)
    }
}