package com.challenge.challengeapp.di

import com.challenge.challengeapp.database.repository.PostUserRepository
import com.challenge.challengeapp.database.repository.PostUserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providePostUserRepository(postUserRepository: PostUserRepositoryImpl): PostUserRepository = postUserRepository
}