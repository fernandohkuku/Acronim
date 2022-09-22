package com.fernando.acronym.core.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AcronymDto(
    @field:Json(name = "sf") val sf: String,
    @field:Json(name = "lfs") val lfs: List<LfDto>
)