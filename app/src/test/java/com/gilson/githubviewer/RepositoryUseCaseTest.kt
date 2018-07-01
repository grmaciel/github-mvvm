package com.gilson.githubviewer

import com.gilson.githubviewer.domain.repository.*
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class RepositoryUseCaseTest {
    lateinit var useCase: RepositoryUseCase
    lateinit var dataStore: RepositoryDataStore

    @Before
    fun startUp() {
        dataStore = mock(RepositoryDataStore::class.java)
        useCase = RepositoryUseCase(dataStore)
    }

    @Test
    fun shouldEmitAtFirstLoadingState() {
        `when`(dataStore.repositories()).thenReturn(Observable.empty())
        val test = useCase.retrieveRepositories().test()
        test.awaitTerminalEvent()
        test.assertNoErrors()
        test.assertValue(RepositoryEvent(RepositoryState.LOADING))
    }

    @Test
    fun shouldWrapReceivedDataAfterLoadingStateIntoFinishedState() {
        val repositories = listOf(Repository(1, "", "", 0, ""))
        val expectedResult = RepositoryEvent(RepositoryState.FINISHED, data = repositories)
        `when`(dataStore.repositories()).thenReturn(Observable.just(repositories))
        val test = useCase.retrieveRepositories().test()
        test.awaitTerminalEvent()
        test.assertNoErrors()
        test.assertValues(RepositoryEvent(RepositoryState.LOADING), expectedResult)
    }

    @Test
    fun shouldWrapErrorIntoErrorState() {
        val exception = Exception("My error")
        `when`(dataStore.repositories()).thenReturn(Observable.error(exception))
        val test = useCase.retrieveRepositories().test()
        test.awaitTerminalEvent()
        test.assertValues(RepositoryEvent(RepositoryState.LOADING), RepositoryEvent(RepositoryState.ERROR, error = exception))
    }
}