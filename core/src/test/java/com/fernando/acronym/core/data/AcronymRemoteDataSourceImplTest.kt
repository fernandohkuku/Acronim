package com.fernando.acronym.core.data

import com.fernando.acronym.core.data.mappers.toDto
import com.fernando.acronym.core.data.remote.api.AcronymService
import com.fernando.acronym.core.data.remote.source.AcronymRemoteDataSourceImpl
import com.fernando.acronym.core.data.repositories.AcronymRemoteDataSource
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
import org.mockito.MockitoAnnotations
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(JUnit4::class)
class AcronymRemoteDataSourceImplTest {

    @Rule
    @JvmField
    var expectedException: ExpectedException = ExpectedException.none()

    @Mock
    private lateinit var service: AcronymService

    private lateinit var remoteDataSource: AcronymRemoteDataSource

    private val acronymBuilder = AcronymBuilder()


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        remoteDataSource = AcronymRemoteDataSourceImpl(service)
    }

    @Test
    fun getAcronymsSuccess() = runTest {
        val acronym = acronymBuilder.build()
        val list = listOf(acronym)
        `when`(service.getAcronyms("asap")).thenReturn(list.map { it.toDto() })

        val result = remoteDataSource.getAcronyms("asap")
        assertEquals(result, list.map { it.toDto() })
    }


    @Test
    fun getAcronymsFailure() = runTest {
        expectedException.expect(HttpException::class.java)

        val exception = Mockito.mock(HttpException::class.java)

        `when`(exception.code())
            .thenReturn(500)

        `when`(service.getAcronyms("asap"))
            .thenThrow(exception)

        remoteDataSource.getAcronyms("asap")
    }
}