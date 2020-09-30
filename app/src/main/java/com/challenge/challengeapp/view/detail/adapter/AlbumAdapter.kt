package com.challenge.challengeapp.view.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.challenge.challengeapp.R
import com.challenge.challengeapp.database.entities.Album
import com.challenge.challengeapp.database.entities.Photo
import com.cruxlab.sectionedrecyclerview.lib.SectionAdapter

class AlbumAdapter(private val album: Album, private val albumPhotos: List<Photo>, var collapsed: Boolean = true)
    : SectionAdapter<ImageViewHolder, AlbumTitleViewHolder>(true, true), OnTitleClickListener {

    override fun onCreateHeaderViewHolder(parent: ViewGroup): AlbumTitleViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.holder_album_header, parent, false)
        return AlbumTitleViewHolder(view, this)
    }

    override fun onBindHeaderViewHolder(holder: AlbumTitleViewHolder)
            = holder.bindHeader(AlbumTitleViewHolder.AlbumTitle(album.title, albumPhotos.size, true))

    override fun getItemCount(): Int = if (collapsed) 0 else albumPhotos.size

    override fun onCreateItemViewHolder(parent: ViewGroup, type: Short): ImageViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.holder_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: ImageViewHolder, position: Int) {
        val photo = albumPhotos[position]
        holder.bindItem(ImageViewHolder.Image(photo.title, photo.url, photo.thumbnailUrl))
    }

    override fun onHeaderClick(albumTitle: AlbumTitleViewHolder.AlbumTitle, shouldCollapse: Boolean, adapterPosition: Int) {
        collapsed=!collapsed
        if (collapsed)
            collapse()
        else
            expand()
    }

    private fun collapse() = notifyItemRangeRemoved(0, albumPhotos.size)

    private fun expand() = notifyItemRangeInserted(0, albumPhotos.size)
}