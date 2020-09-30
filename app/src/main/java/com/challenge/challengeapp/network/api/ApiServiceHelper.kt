package com.challenge.challengeapp.network.api

import com.challenge.challengeapp.network.responses.Albums
import com.challenge.challengeapp.network.responses.Photos
import com.challenge.challengeapp.network.responses.Posts
import com.challenge.challengeapp.network.responses.Users

interface ApiServiceHelper {
    suspend fun getAlbums(): Albums
    suspend fun getPhotos(): Photos
    suspend fun getPosts(): Posts
    suspend fun getUsers(): Users
}