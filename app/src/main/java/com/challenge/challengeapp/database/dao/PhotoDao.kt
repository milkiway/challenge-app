package com.challenge.challengeapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.challenge.challengeapp.database.entities.Photo

@Dao
abstract class PhotoDao: BaseDao<Photo>() {
    @Query("SELECT * FROM photos where id=:id")
    abstract fun getById(id: Int): Photo?
}