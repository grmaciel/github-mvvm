package com.gilson.githubviewer.domain.repository

import io.reactivex.Observable

class RepositoryUseCase(private val repository: RepositoryDataStore) {
    fun retrieveRepositories(): Observable<RepositoryEvent> {
        return repository.repositories()
                .map { RepositoryEvent(RepositoryState.FINISHED, data = it) }
                .startWith(RepositoryEvent(RepositoryState.LOADING))
                .onErrorResumeNext { error: Throwable -> wrapErrorIntoEvent(error) }
    }

    private fun wrapErrorIntoEvent(error: Throwable) =
            Observable.just(RepositoryEvent(RepositoryState.ERROR, error = error))
}