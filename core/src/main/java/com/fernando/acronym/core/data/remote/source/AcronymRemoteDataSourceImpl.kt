package com.fernando.acronym.core.data.remote.source

import com.fernando.acronym.core.data.models.AcronymDto
import com.fernando.acronym.core.data.remote.api.AcronymService
import com.fernando.acronym.core.data.repositories.AcronymRemoteDataSource
import javax.inject.Inject

class AcronymRemoteDataSourceImpl @Inject constructor(
    private val acronymService: AcronymService,
) : AcronymRemoteDataSource {
    override suspend fun getAcronyms(entry: String): List<AcronymDto> =
        acronymService.getAcronyms(entry)
}