package com.gilson.githubviewer.di

import android.arch.lifecycle.ViewModelProvider
import com.gilson.githubviewer.application.GithubViewerApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    NetworkModule::class,
    DataSourceModule::class,
    UseCaseModule::class,
    ViewModelModule::class
])
interface ApplicationComponent {
    fun inject(app: GithubViewerApplication)

    fun provideVMFactory(): ViewModelProvider.Factory

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: GithubViewerApplication): Builder

        fun build(): ApplicationComponent
    }
}