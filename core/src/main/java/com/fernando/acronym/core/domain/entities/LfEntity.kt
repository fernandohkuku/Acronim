package com.fernando.acronym.core.domain.entities

data class LfEntity(
    val lf: String,
    val freq: String,
    val since: String,
    val vars: List<VarEntity>,
){
    data class VarEntity(
        val lf: String,
        val freq: String,
        val since: String,
    )
}