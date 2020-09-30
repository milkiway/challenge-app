package com.challenge.challengeapp.view.detail.adapter

interface OnTitleClickListener {
    fun onHeaderClick(albumTitle: AlbumTitleViewHolder.AlbumTitle, shouldCollapse: Boolean, adapterPosition: Int)
}