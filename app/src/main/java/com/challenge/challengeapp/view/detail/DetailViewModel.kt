package com.challenge.challengeapp.view.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.challenge.challengeapp.database.dao.AlbumDao
import com.challenge.challengeapp.database.dao.AlbumPhotos
import com.challenge.challengeapp.database.entities.Post

class DetailViewModel @ViewModelInject constructor(private val albumDao: AlbumDao): ViewModel() {

    var post: Post? = null
    val albumsLiveData: LiveData<List<AlbumPhotos>>? by lazy { post?.let { albumDao.albumWithPhotosForUser(it.userId) }}

    fun withPost(post: Post) {
        this.post = post
    }
}