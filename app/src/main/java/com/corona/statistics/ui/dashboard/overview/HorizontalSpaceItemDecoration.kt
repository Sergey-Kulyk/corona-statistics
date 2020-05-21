package com.corona.statistics.ui.dashboard.overview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HorizontalSpaceItemDecoration(private val spaceWidth: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.adapter != null && parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
            if (parent.getChildAdapterPosition(view) == 1) {
                outRect.right = spaceWidth - 15
            } else {
                outRect.right = spaceWidth
            }
        }
    }

}

class VerticalSpaceItemDecoration(private val spaceWidth: Int) : ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.adapter != null && parent.getChildAdapterPosition(view) != parent.adapter!!.itemCount - 1) {
            outRect.bottom = spaceWidth
        }
    }

}