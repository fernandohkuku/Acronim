package com.fernando.acronym.core.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LfDto(
    @field:Json(name = "lf") val lf: String,
    @field:Json(name = "freq") val freq: String,
    @field:Json(name = "since") val since: String,
    @field:Json(name = "vars") val vars: List<VarDto>,
) {
    @JsonClass(generateAdapter = true)
    data class VarDto(
        @field:Json(name = "lf") val lf: String,
        @field:Json(name = "freq") val freq: String,
        @field:Json(name = "since") val since: String,
    )
}