package com.fernando.acronym.core.di

import com.fernando.acronym.core.data.remote.api.AcronymService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteModules {
    @Singleton
    @Provides
    fun provideAcronymService(retrofit: Retrofit): AcronymService =
        retrofit.create(AcronymService::class.java)

}