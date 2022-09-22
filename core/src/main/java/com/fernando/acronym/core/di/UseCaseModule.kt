package com.fernando.acronym.core.di

import com.fernando.acronym.core.domain.repositories.AcronymRepository
import com.fernando.acronym.core.domain.usecases.acronym.GetAcronymsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Singleton
    @Provides
    fun provideGetAcronymsUseCase(
        repository: AcronymRepository,
        background: CoroutineDispatcher,
    ) = GetAcronymsUseCase(repository, background)
}