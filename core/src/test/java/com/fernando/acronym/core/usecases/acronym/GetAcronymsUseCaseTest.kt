package com.fernando.acronym.core.usecases.acronym

import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.repositories.AcronymRepository
import com.fernando.acronym.core.domain.usecases.acronym.GetAcronymsUseCase
import com.fernando.acronym.core.domain.usecases.base.Response
import kotlinx.coroutines.*

import kotlinx.coroutines.test.*
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class GetAcronymsUseCaseTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @Mock
    private lateinit var repository: AcronymRepository

    private lateinit var getAcronymsUseCase: GetAcronymsUseCase

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        getAcronymsUseCase = GetAcronymsUseCase(repository, testDispatcher)
    }

    @Test
    fun getSuccess() = runTest {
        val entity = mock(AcronymEntity::class.java)

        val list = listOf(entity)

        `when`(repository.getAcronyms(entry = "asap")).thenReturn(list)

        val result = getAcronymsUseCase(input = "asap")

        assert(result is Response.Success)

        Assert.assertEquals(list, (result as Response.Success).response)
    }

    @Test
    fun getEmptyList() = runTest {
        val list = emptyList<AcronymEntity>()

        `when`(repository.getAcronyms(entry = "asap")).thenReturn(list)

        val result = getAcronymsUseCase(input = "asap")

        assert(result is Response.Success)

        Assert.assertEquals(list, (result as Response.Success).response)

    }

    @Test
    fun getFailure() = runTest {

        `when`(repository.getAcronyms(entry = "asap")).thenThrow(RuntimeException())

        val result = getAcronymsUseCase(input = "asap")

        assert(result is Response.Failure)

        assert((result as Response.Failure).error is RuntimeException)

    }



}