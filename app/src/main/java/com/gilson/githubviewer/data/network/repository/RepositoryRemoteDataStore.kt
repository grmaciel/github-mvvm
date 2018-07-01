package com.gilson.githubviewer.data.network.repository

import com.gilson.githubviewer.domain.repository.Repository
import com.gilson.githubviewer.domain.repository.RepositoryDataStore
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class RepositoryRemoteDataStore(private val rest: RepositoryRest) : RepositoryDataStore {
    override fun repositories(): Observable<List<Repository>> {
        return rest.repositories()
                .flatMap { Observable.fromIterable(it) }
                .take(10)
                .map({ Repository(it.id, it.name, it.description, 0, it.owner.avatarUrl) })
                .flatMap { queryForkCount(it) }
                .toList()
                .subscribeOn(Schedulers.io())
                .toObservable()
    }

    private fun queryForkCount(repository: Repository): Observable<Repository> {
        return rest.repositoryById(repository.id)
                .map { it.forkCount }
                .first(0)
                .map { Repository(repository.id, repository.name, repository.description, it, repository.avatarUrl) }
                .toObservable()
    }
}