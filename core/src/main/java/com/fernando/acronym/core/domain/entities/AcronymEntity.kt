package com.fernando.acronym.core.domain.entities

data class AcronymEntity(
    val sf: String,
    val lfs: List<LfEntity>,
)