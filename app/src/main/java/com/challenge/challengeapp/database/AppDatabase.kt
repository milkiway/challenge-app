package com.challenge.challengeapp.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.challengeapp.database.converters.JsonConverters
import com.challenge.challengeapp.database.dao.AlbumDao
import com.challenge.challengeapp.database.dao.PhotoDao
import com.challenge.challengeapp.database.dao.PostDao
import com.challenge.challengeapp.database.dao.UserDao
import com.challenge.challengeapp.database.entities.Album
import com.challenge.challengeapp.database.entities.Photo
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.database.entities.User

@androidx.room.Database(entities = [Album::class, Photo::class, Post::class, User::class ], version = 1, exportSchema = false)
@TypeConverters(JsonConverters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "database"
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    abstract fun albums(): AlbumDao
    abstract fun photos(): PhotoDao
    abstract fun posts(): PostDao
    abstract fun users(): UserDao
}