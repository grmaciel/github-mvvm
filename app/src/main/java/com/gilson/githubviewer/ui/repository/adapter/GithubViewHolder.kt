package com.gilson.githubviewer.ui.repository.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.gilson.githubviewer.domain.repository.Repository
import com.gilson.githubviewer.ui.image.ImageLoader
import kotlinx.android.synthetic.main.adapter_repository_item.view.*

class GithubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun render(input: Repository, imageLoader: ImageLoader) {
        itemView.txtRepositoryName.text = input.name
        itemView.txtRepositoryDescription.text = input.description
        itemView.txtForkCount.text = input.forkCount.toString()
        imageLoader.loadInto(input.avatarUrl, itemView.imgRepositoryAvatar)
    }
}