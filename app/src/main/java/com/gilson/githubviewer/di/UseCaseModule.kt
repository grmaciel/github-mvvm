package com.gilson.githubviewer.di

import com.gilson.githubviewer.domain.repository.RepositoryDataStore
import com.gilson.githubviewer.domain.repository.RepositoryUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {
    @Provides
    @Singleton
    fun provideGithubUseCase(repository: RepositoryDataStore): RepositoryUseCase {
        return RepositoryUseCase(repository)
    }
}