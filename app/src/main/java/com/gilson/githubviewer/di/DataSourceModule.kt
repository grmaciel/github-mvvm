package com.gilson.githubviewer.di

import com.gilson.githubviewer.data.network.repository.RepositoryRemoteDataStore
import com.gilson.githubviewer.data.network.repository.RepositoryRest
import com.gilson.githubviewer.domain.repository.RepositoryDataStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {
    @Provides
    @Singleton
    fun provideRepositoryDataSource(rest: RepositoryRest): RepositoryDataStore {
        return RepositoryRemoteDataStore(rest)
    }
}