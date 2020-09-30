package com.challenge.challengeapp.database.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.database.entities.PostUserModel

@Dao
abstract class PostDao: BaseDao<Post>() {

    @Query("SELECT * FROM posts")
    abstract fun get(): LiveData<List<Post>>

    @Query("SELECT * FROM posts where id=:id")
    abstract fun getById(id: Int): Post?

    @Query("SELECT posts.*, users.* FROM posts INNER JOIN users ON posts.userId == users.user_id")
    abstract fun getPostsWithUser(): PagingSource<Int, PostUserModel>

    @Query("SELECT posts.*, users.* FROM posts INNER JOIN users ON posts.userId == users.user_id WHERE posts.title LIKE :title")
    abstract fun getPostsByTitle(title: String): PagingSource<Int, PostUserModel>
}