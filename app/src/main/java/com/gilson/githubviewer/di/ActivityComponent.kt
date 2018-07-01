package com.gilson.githubviewer.di

import com.gilson.githubviewer.ui.repository.RepositoryActivity
import com.gilson.githubviewer.ui.image.ImageLoader
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class],
        modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(activity: RepositoryActivity)

    fun provideImageLoader(): ImageLoader
}