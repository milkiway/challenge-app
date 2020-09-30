package com.challenge.challengeapp.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = Album::class,
            parentColumns = ["id"],
            childColumns = ["albumId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["albumId"], name = "albumId", unique = false)
    ])
data class Photo(@PrimaryKey val id: Int, val title: String,
                 val url: String, val thumbnailUrl: String, val albumId: Int)