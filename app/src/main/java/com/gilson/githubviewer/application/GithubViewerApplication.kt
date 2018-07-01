package com.gilson.githubviewer.application

import android.app.Application
import com.gilson.githubviewer.di.ApplicationComponent
import com.gilson.githubviewer.di.DaggerApplicationComponent

class GithubViewerApplication : Application() {
    var component: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        component().inject(this)
    }

    fun component(): ApplicationComponent {
        if (component == null) {
            component = DaggerApplicationComponent
                    .builder()
                    .application(this)
                    .build()
        }
        return component!!
    }
}