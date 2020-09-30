package com.challenge.challengeapp.view.master.adapter

import androidx.recyclerview.widget.DiffUtil
import com.challenge.challengeapp.database.entities.PostUserModel

object PostUserComparator : DiffUtil.ItemCallback<PostUserModel>() {

    override fun areContentsTheSame(oldItem: PostUserModel, newItem: PostUserModel) = oldItem == newItem

    override fun areItemsTheSame(oldItem: PostUserModel, newItem: PostUserModel) = oldItem.post.id == newItem.post.id
}