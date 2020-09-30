package com.challenge.challengeapp.view.detail.adapter

import android.view.View
import android.widget.TextView
import com.cruxlab.sectionedrecyclerview.lib.BaseSectionAdapter

class SimpleTextViewHolder(itemView: View) : BaseSectionAdapter.ItemViewHolder(itemView) {

    fun bindItem(item: Text) {
        (itemView as TextView).text = item.text
    }

    class Text(val text: String)
}