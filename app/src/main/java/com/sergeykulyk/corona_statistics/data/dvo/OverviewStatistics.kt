package com.sergeykulyk.corona_statistics.data.dvo

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class OverviewStatistics(
    @DrawableRes val background: Int,
    @StringRes val title: Int,
    val number: String,
    val percent: String
)