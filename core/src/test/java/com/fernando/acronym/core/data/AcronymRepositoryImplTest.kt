package com.fernando.acronym.core.data

import com.fernando.acronym.core.data.exceptions.NotFoundException
import com.fernando.acronym.core.data.mappers.toDto
import com.fernando.acronym.core.data.mappers.toEntity
import com.fernando.acronym.core.data.models.AcronymDto
import com.fernando.acronym.core.data.repositories.AcronymRemoteDataSource
import com.fernando.acronym.core.data.repositories.AcronymRepositoryImpl
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.utils.AcronymBuilder
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class AcronymRepositoryImplTest {


    @Mock
    private lateinit var remoteDataSource: AcronymRemoteDataSource

    private lateinit var repository: AcronymRepositoryImpl

    private val acronymBuilder = AcronymBuilder()

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = AcronymRepositoryImpl(remoteDataSource)
    }


    @Test
    fun getAcronyms() = runTest {
        val acronymEntity = acronymBuilder.build()

        val listEntity = listOf(acronymEntity)

        `when`(remoteDataSource.getAcronyms("asap")).thenReturn(listEntity.map { it.toDto() })

        val response = repository.getAcronyms("asap")

        assertEquals(listEntity, response)
    }


}