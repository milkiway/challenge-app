package com.challenge.challengeapp.view.master.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.challenge.challengeapp.database.entities.PostUserModel
import kotlinx.android.synthetic.main.holder_post.view.*

class PostViewHolder(itemView: View, private val selectionListener: OnPostSelectionListener, private val deletedListener: OnPostDeletedListener)
    : RecyclerView.ViewHolder(itemView) {

    fun bind(postUser: PostUserModel?) {
        postUser?.let { item ->
            itemView.title.text = item.post.title
            itemView.author_email.text = item.user.email
            itemView.delete.setOnClickListener { deletedListener.onPostDeleted(item.post) }
            itemView.setOnClickListener { selectionListener.onPostSelected(item.post) }
        }
    }
}