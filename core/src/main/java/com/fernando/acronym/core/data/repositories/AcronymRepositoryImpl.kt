package com.fernando.acronym.core.data.repositories

import com.fernando.acronym.core.data.mappers.toEntity
import com.fernando.acronym.core.data.models.AcronymDto
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.repositories.AcronymRepository
import javax.inject.Inject


internal class AcronymRepositoryImpl @Inject constructor(
    private val remoteDataSource: AcronymRemoteDataSource,
) : AcronymRepository {
    override suspend fun getAcronyms(entry: String): List<AcronymEntity> =
        remoteDataSource.getAcronyms(entry).map { it.toEntity() }
}


internal interface AcronymRemoteDataSource {
    suspend fun getAcronyms(entry: String): List<AcronymDto>
}