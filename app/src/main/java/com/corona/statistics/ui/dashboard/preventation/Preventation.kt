package com.corona.statistics.ui.dashboard.preventation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Preventation(
    @DrawableRes var icon: Int,
    @StringRes val title: Int,
    @StringRes var description: Int
)