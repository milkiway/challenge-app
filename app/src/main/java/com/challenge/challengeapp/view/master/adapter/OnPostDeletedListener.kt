package com.challenge.challengeapp.view.master.adapter

import com.challenge.challengeapp.database.entities.Post

interface OnPostDeletedListener {
    fun onPostDeleted(post: Post)
}