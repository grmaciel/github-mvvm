package com.gilson.githubviewer.ui.repository

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.gilson.githubviewer.R
import com.gilson.githubviewer.application.GithubViewerApplication
import com.gilson.githubviewer.di.ActivityModule
import com.gilson.githubviewer.di.DaggerActivityComponent
import com.gilson.githubviewer.domain.repository.Repository
import com.gilson.githubviewer.domain.repository.RepositoryEvent
import com.gilson.githubviewer.domain.repository.RepositoryState
import com.gilson.githubviewer.ui.RecyclerDecoratorSpace
import com.gilson.githubviewer.ui.image.ImageLoader
import com.gilson.githubviewer.ui.repository.adapter.RepositoryAdapter
import kotlinx.android.synthetic.main.activity_repository.*
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var imageLoader: ImageLoader
    lateinit var viewModel: RepositoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)
        inject()
        viewModel = viewModelFactory.create(RepositoryViewModel::class.java)
        setupAdapter()
        observeDataUpdates()
        setupReload()
    }

    private fun inject() {
        DaggerActivityComponent.builder()
                .applicationComponent((application as GithubViewerApplication).component())
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)
    }

    private fun setupAdapter() {
        listRepositories.layoutManager = LinearLayoutManager(this)
        listRepositories.addItemDecoration(RecyclerDecoratorSpace())
        listRepositories.adapter = RepositoryAdapter(mutableListOf(), imageLoader)
    }

    private fun setupReload() {
        swipeLayout.setOnRefreshListener {
            adapter().clear()
            viewModel.reload()
        }
    }

    private fun observeDataUpdates() {
        viewModel.githubLiveData()
                .observe(this, Observer { handleResult(it!!) })
    }

    private fun handleResult(it: RepositoryEvent) {
        when (it.state) {
            RepositoryState.LOADING -> displayLoading()
            RepositoryState.FINISHED -> displayData(it.data!!)
            RepositoryState.ERROR -> displayError(it.error!!)
        }
    }

    private fun displayLoading() {
        if (!swipeLayout.isRefreshing) {
            swipeLayout.isRefreshing = true
        }
    }

    private fun hideLoading() {
        swipeLayout.isRefreshing = false
    }

    private fun displayData(data: List<Repository>) {
        adapter().addRepositories(data)
        hideLoading()
    }

    private fun displayError(error: Throwable) {
        hideLoading()
        var messageId = R.string.message_error
        if (error is HttpException && error.code() == 403) {
            messageId = R.string.message_error_rate_limit
        }
        Toast.makeText(this, messageId, Toast.LENGTH_SHORT).show()
    }

    private fun adapter() = (listRepositories.adapter as RepositoryAdapter)
}