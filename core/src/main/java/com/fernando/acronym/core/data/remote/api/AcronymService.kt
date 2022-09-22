package com.fernando.acronym.core.data.remote.api

import com.fernando.acronym.core.data.models.AcronymDto
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymService {
    @GET("/software/acromine/dictionary.py/")
    suspend fun getAcronyms(@Query("sf") entry:String):List<AcronymDto>
}