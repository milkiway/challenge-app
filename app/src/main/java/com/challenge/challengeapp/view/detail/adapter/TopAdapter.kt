package com.challenge.challengeapp.view.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.cruxlab.sectionedrecyclerview.lib.SimpleSectionAdapter
import java.util.*

class TopAdapter(@LayoutRes val layoutRes: Int, text: String) : SimpleSectionAdapter<SimpleTextViewHolder>() {
    private val items = Collections.singletonList(SimpleTextViewHolder.Text(text))

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateItemViewHolder(parent: ViewGroup, type: Short): SimpleTextViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
        return SimpleTextViewHolder(view)
    }

    override fun onBindItemViewHolder(holder: SimpleTextViewHolder, position: Int) {
        holder.bindItem(items[position])
    }
}