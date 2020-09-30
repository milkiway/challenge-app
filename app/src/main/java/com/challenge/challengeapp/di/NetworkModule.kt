package com.challenge.challengeapp.di

import com.challenge.challengeapp.network.api.ApiServiceHelper
import com.challenge.challengeapp.network.api.ApiServiceHelperImpl
import com.challenge.challengeapp.network.api.SampleApi
import com.challenge.challengeapp.network.RestConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {
    @Provides
    fun provideApiBaseUrl() = RestConfig.API_BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        if (RestConfig.DEBUG) { // debug ON
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder()
                .addInterceptor(logger)
                .readTimeout(RestConfig.READ_TIMEOUT_DEBUG, TimeUnit.SECONDS)
                .connectTimeout(RestConfig.CONNECT_TIMEOUT_DEBUG, TimeUnit.SECONDS)
                .build()
        } else // debug OFF
            OkHttpClient.Builder()
                .readTimeout(RestConfig.READ_TIMEOUT_RELEASE, TimeUnit.SECONDS)
                .connectTimeout(RestConfig.CONNECT_TIMEOUT_RELEASE, TimeUnit.SECONDS)
                .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BaseURL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BaseURL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(SampleApi::class.java)

    @Provides
    @Singleton
    fun provideApiServiceHelper(apiHelper: ApiServiceHelperImpl): ApiServiceHelper = apiHelper
}