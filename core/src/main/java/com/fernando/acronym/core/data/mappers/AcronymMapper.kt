package com.fernando.acronym.core.data.mappers

import com.fernando.acronym.core.data.models.AcronymDto
import com.fernando.acronym.core.data.models.LfDto
import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.entities.LfEntity
import java.net.HttpURLConnection
import kotlin.math.sin


internal fun AcronymDto.toEntity() = AcronymEntity(
    lfs = lfs.map { it.toEntity() },
    sf = sf,
)

internal fun LfDto.toEntity() = LfEntity(
    lf = lf,
    freq = freq,
    since = since,
    vars = vars.map { it.toEntity() }
)

internal fun LfDto.VarDto.toEntity() = LfEntity.VarEntity(
    lf = lf,
    freq = freq,
    since = since
)

internal fun AcronymEntity.toDto() = AcronymDto(
    lfs = lfs.map { it.toDto() },
    sf = sf,
)

internal fun LfEntity.toDto() = LfDto(
    lf = lf,
    freq = freq,
    since = since,
    vars = vars.map { it.toEntity() }
)

internal fun LfEntity.VarEntity.toEntity() = LfDto.VarDto(
    lf = lf,
    freq = freq,
    since = since
)