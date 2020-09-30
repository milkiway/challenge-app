package com.challenge.challengeapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "posts",
        foreignKeys = [ForeignKey(
                entity = User::class,
                parentColumns = ["user_id"],
                childColumns = ["userId"],
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE)
        ],
        indices = [
            Index(value = ["userId"], name = "postUserId", unique = false)
        ])
data class Post(@PrimaryKey val id: Int, val title: String, val body: String, val userId: Int)