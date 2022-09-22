package com.fernando.acronym.core.domain.usecases.acronym

import com.fernando.acronym.core.domain.entities.AcronymEntity
import com.fernando.acronym.core.domain.repositories.AcronymRepository
import com.fernando.acronym.core.domain.usecases.base.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class GetAcronymsUseCase @Inject constructor(
    private val repository: AcronymRepository,
    background:CoroutineDispatcher
) :UseCase<List<AcronymEntity>, String>(background){
    override suspend fun run(input: String?): List<AcronymEntity> {
        requireNotNull(input) { "entry can't be null" }
        return  repository.getAcronyms(entry = input)
    }
}