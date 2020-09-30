package com.challenge.challengeapp.view.master.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.challenge.challengeapp.R
import com.challenge.challengeapp.database.entities.PostUserModel

class PostsAdapter(private val onPostSelectionListener: OnPostSelectionListener, private val onPostDeletedListener: OnPostDeletedListener)
    : PagingDataAdapter<PostUserModel, PostViewHolder>(PostUserComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.holder_post, parent, false)
        return PostViewHolder(itemView, onPostSelectionListener, onPostDeletedListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) = holder.bind(getItem(position))
}