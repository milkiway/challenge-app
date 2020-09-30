package com.challenge.challengeapp.di

import android.content.Context
import com.challenge.challengeapp.database.AppDatabase
import com.challenge.challengeapp.database.dao.AlbumDao
import com.challenge.challengeapp.database.dao.PhotoDao
import com.challenge.challengeapp.database.dao.PostDao
import com.challenge.challengeapp.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase = AppDatabase.getDatabase(appContext)

    @Provides
    @Singleton
    fun provideAlbumsDao(appDatabase: AppDatabase): AlbumDao = appDatabase.albums()

    @Provides
    @Singleton
    fun providePhotosDao(appDatabase: AppDatabase): PhotoDao = appDatabase.photos()

    @Provides
    @Singleton
    fun providePostsDao(appDatabase: AppDatabase): PostDao = appDatabase.posts()

    @Provides
    @Singleton
    fun provideUsersDao(appDatabase: AppDatabase): UserDao = appDatabase.users()
}