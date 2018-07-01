package com.gilson.githubviewer.ui

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.View

class RecyclerDecoratorSpace : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView?, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(0, 0, 0,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f,
                        view.resources.displayMetrics).toInt())
    }
}