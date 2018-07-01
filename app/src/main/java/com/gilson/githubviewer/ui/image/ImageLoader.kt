package com.gilson.githubviewer.ui.image

import android.widget.ImageView

interface ImageLoader {
    fun loadInto(url: String, target: ImageView)
}