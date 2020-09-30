package com.challenge.challengeapp.view.detail.adapter

import android.content.Context
import android.view.View
import com.challenge.challengeapp.R
import com.cruxlab.sectionedrecyclerview.lib.BaseSectionAdapter
import kotlinx.android.synthetic.main.holder_album_header.view.*

class AlbumTitleViewHolder(itemView: View, private val onTitleClickListener: OnTitleClickListener)
    : BaseSectionAdapter.HeaderViewHolder(itemView) {

    fun bindHeader(item: AlbumTitle) {
        itemView.title.text = item.title
        itemView.photo_num.text = getPhotosNum(itemView.context, item.photosTotal)

        itemView.setOnClickListener {
            item.isCollapsed = !item.isCollapsed
            onTitleClickListener.onHeaderClick(item, item.isCollapsed, globalAdapterPosition)
        }
    }

    private fun getPhotosNum(context: Context, photosTotal: Int)
            : CharSequence = context.getString(if (photosTotal==1) R.string.photo_num_one else R.string.photo_num, photosTotal)

    class AlbumTitle(val title: String, val photosTotal: Int, var isCollapsed: Boolean)
}