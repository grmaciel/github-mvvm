package com.gilson.githubviewer.ui.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.gilson.githubviewer.domain.repository.RepositoryEvent
import com.gilson.githubviewer.domain.repository.RepositoryUseCase
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class GithubViewModel @Inject constructor(private val useCase: RepositoryUseCase) : ViewModel() {
    private val githubLiveData: MutableLiveData<RepositoryEvent> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()

    init {
        compositeDisposable.add(retrieveRepositories())
    }

    fun reload() {
        compositeDisposable.add(retrieveRepositories())
    }

    fun githubLiveData(): LiveData<RepositoryEvent> {
        return githubLiveData
    }

    private fun retrieveRepositories(): Disposable {
        compositeDisposable.clear()
        return useCase.retrieveRepositories()
                .subscribe({ githubLiveData.postValue(it) },
                        { Log.e("Error", "Unexpected error $it") })
    }
}