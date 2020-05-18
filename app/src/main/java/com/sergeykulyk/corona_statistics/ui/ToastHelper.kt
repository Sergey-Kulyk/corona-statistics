package com.sergeykulyk.corona_statistics.ui

import android.widget.Toast
import androidx.annotation.StringRes
import com.sergeykulyk.corona_statistics.getAppContext

object ToastHelper {

    private var toast: Toast? = null

    fun showToast(@StringRes messageId: Int) {
        toast?.cancel()
        toast = Toast.makeText(getAppContext(), messageId, Toast.LENGTH_SHORT)
        toast?.show()
    }
}