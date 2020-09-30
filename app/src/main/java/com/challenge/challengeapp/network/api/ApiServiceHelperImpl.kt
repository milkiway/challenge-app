package com.challenge.challengeapp.network.api

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiServiceHelperImpl @Inject constructor(private val apiService: SampleApi) :
    ApiServiceHelper {
    override suspend fun getAlbums() = apiService.fetchAlbums()
    override suspend fun getPhotos() = apiService.fetchPhotos()
    override suspend fun getPosts() = apiService.fetchPosts()
    override suspend fun getUsers() = apiService.fetchUsers()
}