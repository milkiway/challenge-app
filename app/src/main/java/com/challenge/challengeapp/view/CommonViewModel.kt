package com.challenge.challengeapp.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.challenge.challengeapp.database.entities.Post

class CommonViewModel: ViewModel() {
    var selectedPost: Post? = null
    val onDeletePost = MutableLiveData<Post>()
}