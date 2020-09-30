package com.challenge.challengeapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.challenge.challengeapp.database.entities.User

@Dao
abstract class UserDao: BaseDao<User>() {
    @Query("SELECT * FROM USERS where user_id=:id")
    abstract fun getById(id: Int): User?
}