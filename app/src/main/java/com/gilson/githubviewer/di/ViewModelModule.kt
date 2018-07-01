package com.gilson.githubviewer.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gilson.githubviewer.ui.ViewModelFactory
import com.gilson.githubviewer.ui.repository.GithubViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(GithubViewModel::class)
    abstract fun bindBookViewModel(eventViewModel: GithubViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}