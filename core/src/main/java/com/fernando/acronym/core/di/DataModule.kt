package com.fernando.acronym.core.di

import com.fernando.acronym.core.data.remote.source.AcronymRemoteDataSourceImpl
import com.fernando.acronym.core.data.repositories.AcronymRemoteDataSource
import com.fernando.acronym.core.data.repositories.AcronymRepositoryImpl
import com.fernando.acronym.core.domain.repositories.AcronymRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    internal abstract fun bindAcronymRemoteDataSource(
        remoteDataSourceImpl: AcronymRemoteDataSourceImpl,
    ): AcronymRemoteDataSource

    @Binds
    internal abstract fun bindAcronymRepository(
        repositoryImpl: AcronymRepositoryImpl,
    ): AcronymRepository
}