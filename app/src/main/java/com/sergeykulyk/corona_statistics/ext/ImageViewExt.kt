package com.sergeykulyk.corona_statistics.ext

import android.graphics.BitmapFactory
import android.widget.ImageView
import java.net.URL

fun ImageView.loadUrl(url: String) {

    val url = URL(url)
    val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())
    setImageBitmap(bmp)
}