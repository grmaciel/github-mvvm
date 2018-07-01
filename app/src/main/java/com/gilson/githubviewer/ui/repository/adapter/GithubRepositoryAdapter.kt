package com.gilson.githubviewer.ui.repository.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gilson.githubviewer.R
import com.gilson.githubviewer.domain.repository.Repository
import com.gilson.githubviewer.ui.image.ImageLoader

class GithubRepositoryAdapter(private var data: MutableList<Repository>,
                              private val imageLoader: ImageLoader) : RecyclerView.Adapter<GithubViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            GithubViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.adapter_repository_item, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GithubViewHolder, position: Int) {
        holder.render(data[position], imageLoader)
    }

    fun addRepositories(repositories: List<Repository>) {
        data.clear()
        data.addAll(repositories)
        notifyDataSetChanged()
    }

    fun clear() {
        data = mutableListOf()
        notifyDataSetChanged()
    }
}
