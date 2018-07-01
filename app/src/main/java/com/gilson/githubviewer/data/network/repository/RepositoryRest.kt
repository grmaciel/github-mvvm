package com.gilson.githubviewer.data.network.repository

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface RepositoryRest {
    @GET("/repositories")
    fun repositories(): Observable<List<GithubResponse>>

    @GET("/repositories/{id}")
    fun repositoryById(@Path("id") id: Int): Observable<GithubResponse>
}