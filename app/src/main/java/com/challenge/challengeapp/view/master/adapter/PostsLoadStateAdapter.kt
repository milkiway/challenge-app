package com.challenge.challengeapp.view.master.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class PostsLoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<PostsLoadStateViewHolder>() {
    override fun onBindViewHolder(holder: PostsLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PostsLoadStateViewHolder {
        return PostsLoadStateViewHolder.create(parent, retry)
    }
}