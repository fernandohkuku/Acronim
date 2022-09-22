package com.fernando.acronym.core.utils

import com.fernando.acronym.core.data.models.AcronymDto
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.entities.LfEntity

data class AcronymBuilder(
    val sf: String = "Asap",
    val lfs: List<LfEntity> = emptyList(),
) {
    fun build() = AcronymEntity(
        sf,
        lfs
    )
}