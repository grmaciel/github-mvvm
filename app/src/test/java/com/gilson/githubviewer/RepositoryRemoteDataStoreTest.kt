package com.gilson.githubviewer

import com.gilson.githubviewer.data.network.repository.GithubOwnerResponse
import com.gilson.githubviewer.data.network.repository.GithubResponse
import com.gilson.githubviewer.data.network.repository.RepositoryRemoteDataStore
import com.gilson.githubviewer.data.network.repository.RepositoryRest
import com.nhaarman.mockito_kotlin.times
import io.reactivex.Observable
import org.hamcrest.core.Is
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

class RepositoryRemoteDataStoreTest {
    lateinit var dataStore: RepositoryRemoteDataStore
    lateinit var rest: RepositoryRest

    @Before
    fun startUp() {
        this.rest = Mockito.mock(RepositoryRest::class.java)
        this.dataStore = RepositoryRemoteDataStore(this.rest)
    }

    @Test
    fun shouldNotReturnMoreThan10Results() {
        `when`(rest.repositories()).thenReturn(Observable.just(mockResponse()))
        `when`(rest.repositoryById(1)).thenReturn(Observable.just(GithubResponse(1, "", "", "", GithubOwnerResponse(""), 1, 1)))
        val test = dataStore.repositories().test()
        test.awaitTerminalEvent()
        test.assertNoErrors()
        val result = test.values().first()
        Assert.assertThat(result.count(), Is.`is`(10))
    }


    @Test
    fun shouldQueryForkCountFromRepositoryDetailForEachElement() {
        `when`(rest.repositories()).thenReturn(Observable.just(mockResponse()))
        `when`(rest.repositoryById(1)).thenReturn(Observable.just(GithubResponse(1, "", "", "", GithubOwnerResponse(""), 1, 1)))
        val test = dataStore.repositories().test()
        test.awaitTerminalEvent()
        test.assertNoErrors()
        Mockito.verify(rest, times(10)).repositoryById(1)
    }

    private fun mockResponse(): List<GithubResponse> {
        return listOf(GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null),
                GithubResponse(1, "", "", "", GithubOwnerResponse(""), null, null))
    }
}