package com.challenge.challengeapp.network.api

import com.challenge.challengeapp.network.responses.Albums
import com.challenge.challengeapp.network.responses.Photos
import com.challenge.challengeapp.network.responses.Posts
import com.challenge.challengeapp.network.responses.Users
import retrofit2.http.GET

interface SampleApi {

    @GET("albums")
    suspend fun fetchAlbums(): Albums

    @GET("photos")
    suspend fun fetchPhotos(): Photos

    @GET("posts/")
    suspend fun fetchPosts(): Posts

    @GET("users")
    suspend fun fetchUsers(): Users
}