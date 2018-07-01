package com.gilson.githubviewer.di

import android.content.Context
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.gilson.githubviewer.ui.image.ImageLoader
import com.gilson.githubviewer.ui.image.PicassoImageLoader
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: AppCompatActivity) {

    @Provides
    @ForActivity
    fun provideContext(): Context {
        return activity
    }

    @Provides
    @ForActivity
    fun provideFragmentManager(): FragmentManager {
        return activity.supportFragmentManager
    }

    @Provides
    fun providesImageLoader(): ImageLoader {
        return PicassoImageLoader()
    }
}