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

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var repository: AcronymRepository

    @Mock
    private lateinit var getAcronymsUseCase: GetAcronymsUseCase

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        getAcronymsUseCase = GetAcronymsUseCase(repository, testDispatcher)
    }

    @Test
    fun getSuccess() = runTest {
        val entity = mock(AcronymEntity::class.java)

        val list = listOf(entity)

        `when`(repository.getAcronyms(entry = "asap")).thenReturn(list)

        val result = repository.getAcronyms("asap")

        Assert.assertEquals(list, result)
    }


}