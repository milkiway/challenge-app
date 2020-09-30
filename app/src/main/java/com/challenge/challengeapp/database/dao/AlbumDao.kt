package com.challenge.challengeapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.challenge.challengeapp.database.entities.Album

@Dao
abstract class AlbumDao: BaseDao<Album>() {
    @Query("SELECT * FROM albums where id=:id")
    abstract suspend fun getById(id: Int): Album?

    @Transaction
    @Query("SELECT * FROM albums where userId=:userId")
    abstract fun albumWithPhotosForUser(userId: Int): LiveData<List<AlbumPhotos>>
}