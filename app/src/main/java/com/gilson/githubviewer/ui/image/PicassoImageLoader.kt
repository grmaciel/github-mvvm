package com.gilson.githubviewer.ui.image

import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoImageLoader : ImageLoader {
    override fun loadInto(url: String, target: ImageView) {
        Picasso.get().load(url).into(target)
    }
}