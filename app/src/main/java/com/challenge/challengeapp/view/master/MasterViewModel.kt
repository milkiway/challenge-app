package com.challenge.challengeapp.view.master

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.challenge.challengeapp.database.repository.PostUserRepository
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.database.entities.PostUserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MasterViewModel @ViewModelInject constructor(private val repository: PostUserRepository) : ViewModel() {
    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<PostUserModel>>? = null

    fun searchRepo(queryString: String?): Flow<PagingData<PostUserModel>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<PostUserModel>> = repository.filter(queryString).cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) { repository.deletePost(post) }
    }
}