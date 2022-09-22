package com.fernando.acronym.di

import com.fernando.acronym.core.infrastructure.handlers.error.ApiErrorHandler
import com.fernando.acronym.core.infrastructure.handlers.error.ErrorHandler
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ErrorModule {
    @Provides
    @Singleton
    fun provideApiErrorHandler() = ApiErrorHandler()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class ErrorHandlerModule {
    @Binds
    abstract fun provideErrorHandler(
        apiErrorHandler: ApiErrorHandler,
    ): ErrorHandler

}