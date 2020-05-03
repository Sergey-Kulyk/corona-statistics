package com.sergeykulyk.map_statistics

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.annotation.ColorRes
import com.sergeykulyk.map_statistics.vectorchildfinder.VectorChildFinder
import com.sergeykulyk.map_statistics.vectorchildfinder.VectorDrawableCompat

class MapStatisticsView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var countries: List<MapCountry> = emptyList()

    private var imageView: ImageView = ImageView(context)

    init {
        imageView.setImageResource(R.drawable.im_map_statistics)
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        addView(imageView)
    }

    fun invalid() {
        val maxNumber = countries.maxBy { it.number }?.number ?: 0
        val vector = VectorChildFinder(context, R.drawable.im_map_statistics, imageView)

        for (country in countries) {
            val percent = country.number * 100 / maxNumber
            Log.d("Percent", country.slug + " " + percent)
            val color = getColor(country.number, maxNumber)
            try {
                val path1 = vector.findPathByName(country.slug)
                path1.fillColor = context.resources.getColor(color)
            } catch (e: Exception) {
                Log.e("MapStatisticsView", "Unknown country slug ${country.slug}")
            }

            try {
                val path2 = vector.findGroupByName(country.slug)
                path2.mChildren.forEach {
                    (it as VectorDrawableCompat.VFullPath).fillColor = context.resources.getColor(color)
                }
            } catch (e: Exception) {
            }
        }
        imageView.invalidate()
    }

    @ColorRes
    private fun getColor(number: Int, maxNumber: Int): Int {
        val percent = number * 100 / maxNumber

        return when (percent) {
            in 10..20 -> R.color.percent20
            in 20..30 -> R.color.percent30
            in 30..40 -> R.color.percent40
            in 40..50 -> R.color.percent50
            in 50..60 -> R.color.percent60
            in 60..70 -> R.color.percent70
            in 70..80 -> R.color.percent80
            in 80..90 -> R.color.percent90
            in 90..100 -> R.color.percent100
            else -> R.color.percent20
        }
    }

}