package com.fernando.acronym.core.domain.repositories

import com.fernando.acronym.core.domain.entities.AcronymEntity

interface AcronymRepository {
    suspend fun getAcronyms(entry: String): List<AcronymEntity>
}