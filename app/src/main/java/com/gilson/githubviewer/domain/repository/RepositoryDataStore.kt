package com.gilson.githubviewer.domain.repository

import io.reactivex.Observable

interface RepositoryDataStore {
    fun repositories(): Observable<List<Repository>>
}