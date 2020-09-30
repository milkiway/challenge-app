package com.challenge.challengeapp.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(users: List<T>)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract suspend fun insert(t: T)

    @Update
    abstract suspend fun update(t: T)

    @Delete
    abstract suspend fun delete(t: T)
}