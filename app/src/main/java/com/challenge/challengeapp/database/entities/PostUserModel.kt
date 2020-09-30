package com.challenge.challengeapp.database.entities

import androidx.room.Embedded

data class PostUserModel(@Embedded val post: Post, @Embedded val user: User)