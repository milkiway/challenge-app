package com.challenge.challengeapp.database.repository

import androidx.paging.PagingData
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.database.entities.PostUserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Singleton

@Singleton
interface PostUserRepository {
    fun filter(filterText: String?): Flow<PagingData<PostUserModel>>

    suspend fun deletePost(post: Post)
}