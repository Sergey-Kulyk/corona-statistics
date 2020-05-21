package com.corona.statistics.ui.custom

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class CustomSwipeRefresh(context: Context, attrs: AttributeSet) :
    SwipeRefreshLayout(context, attrs) {

    private var mTouchSlop = 0
    private var mPrevX = 0f

    init {
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop

    }

    @SuppressLint("Recycle")
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> mPrevX = MotionEvent.obtain(event).x
            MotionEvent.ACTION_MOVE -> {
                val eventX: Float = event.x
                val xDiff = kotlin.math.abs(eventX - mPrevX)
                if (xDiff > mTouchSlop) {
                    return false
                }
            }
        }
        return super.onInterceptTouchEvent(event)
    }
}
