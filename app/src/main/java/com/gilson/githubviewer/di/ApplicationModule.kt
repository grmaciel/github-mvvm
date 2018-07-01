package com.gilson.githubviewer.di

import android.content.Context
import com.gilson.githubviewer.application.GithubViewerApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    @Provides
    fun provideApplicationContext(application: GithubViewerApplication): Context {
        return application.applicationContext
    }
}