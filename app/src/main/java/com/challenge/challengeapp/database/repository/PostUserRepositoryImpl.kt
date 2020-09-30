package com.challenge.challengeapp.database.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.challenge.challengeapp.MasterRemoteMediator
import com.challenge.challengeapp.network.api.ApiServiceHelper
import com.challenge.challengeapp.database.AppDatabase
import com.challenge.challengeapp.database.dao.PostDao
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.database.entities.PostUserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostUserRepositoryImpl @Inject constructor(private val appDatabase: AppDatabase, private val postDao: PostDao, private val apiServiceHelper: ApiServiceHelper): PostUserRepository {

    override fun filter(filterText: String?): Flow<PagingData<PostUserModel>> {
        return getPager(filterText)
    }

    override suspend fun deletePost(post: Post) {
        postDao.delete(post)
    }

    private fun getPager(query: String?): Flow<PagingData<PostUserModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            remoteMediator = MasterRemoteMediator(appDatabase, apiServiceHelper)
        ) {
            getPageSource(query)
        }.flow
    }

    private fun getPageSource(query: String?): PagingSource<Int, PostUserModel> {
        return if (query.isNullOrEmpty()) postDao.getPostsWithUser() else postDao.getPostsByTitle("%$query%")
    }

    companion object {
        private const val NETWORK_PAGE_SIZE = 30
    }
}