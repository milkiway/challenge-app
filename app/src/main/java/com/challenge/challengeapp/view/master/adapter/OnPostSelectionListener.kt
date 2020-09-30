package com.challenge.challengeapp.view.master.adapter

import com.challenge.challengeapp.database.entities.Post

interface OnPostSelectionListener {
    fun onPostSelected(post: Post)
}