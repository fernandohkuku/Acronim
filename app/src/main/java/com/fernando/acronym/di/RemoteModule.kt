package com.fernando.acronym.di

import android.content.Context
import com.fernando.acronym.BuildConfig
import com.fernando.acronym.core.data.exceptions.NotInternetException
import com.fernando.acronym.core.infrastructure.handlers.error.ErrorHandler
import com.fernando.acronym.ui_ktx.content.isInternetAvailable
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

const val INTERCEPTOR_ERROR = "interceptor_error"
const val INTERCEPTOR_CONNECTION = "interceptor_connection"

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {
    @Provides
    @Singleton
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

    @Provides
    @Singleton
    @Named(INTERCEPTOR_ERROR)
    fun provideInterceptorError(
        errorHandler: ErrorHandler,
    ): Interceptor = Interceptor { chain ->
        val response = chain.proceed(chain.request())
        errorHandler.throwFromCode(response.code(), response.body()?.charStream())
        response
    }

    @Provides
    @Singleton
    @Named(INTERCEPTOR_CONNECTION)
    fun provideInterceptorConnection(@ApplicationContext context: Context) = Interceptor { chain ->
        if (!context.isInternetAvailable()) {
            throw NotInternetException()
        }
        chain.proceed(chain.request())
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @Named(INTERCEPTOR_ERROR)
        interceptorError: Interceptor,
        @Named(INTERCEPTOR_CONNECTION)
        interceptorConnection: Interceptor,
    ): OkHttpClient =
        OkHttpClient.Builder().apply {
            if(BuildConfig.DEBUG){
                addInterceptor(OkHttpProfilerInterceptor())
            }
            addInterceptor(interceptorError)
            addInterceptor(interceptorConnection)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }.build()

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String, moshi: Moshi, client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(baseUrl)
            .client(client)
            .build()
}