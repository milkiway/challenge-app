package com.challenge.challengeapp.database.dao

import androidx.room.Embedded
import androidx.room.Relation
import com.challenge.challengeapp.database.entities.Album
import com.challenge.challengeapp.database.entities.Photo

class AlbumPhotos {
    @Embedded
    var album: Album? = null
    @Relation(parentColumn = "id", entityColumn = "albumId")
    var photos: List<Photo> = emptyList()
}