package com.challenge.challengeapp.view.detail.adapter

import android.view.View
import android.widget.ImageView
import androidx.navigation.Navigation
import coil.load
import coil.transform.RoundedCornersTransformation
import com.challenge.challengeapp.R
import com.challenge.challengeapp.view.detail.DetailFragmentDirections
import com.cruxlab.sectionedrecyclerview.lib.BaseSectionAdapter

class ImageViewHolder(itemView: View) : BaseSectionAdapter.ItemViewHolder(itemView) {

    fun bindItem(image: Image) {
        val imageView =  itemView as ImageView
        imageView.load(image.thumbnailUrl) {
            crossfade(true)
            placeholder(PLACEHOLDER_DRAWABLE)
            transformations(RoundedCornersTransformation(ROUND_CORNER_RADIUS))
        }
        imageView.setOnClickListener { openImagePreview(imageView, image.url) }
    }

    private fun openImagePreview(imageView: ImageView, imageUrl: String) {
        Navigation.findNavController(imageView)
            .navigate(DetailFragmentDirections.actionDetailFragmentToImageViewFragment(imageUrl))
    }

    class Image(val title: String, val url: String, val thumbnailUrl: String)

    companion object {
        const val PLACEHOLDER_DRAWABLE = R.drawable.ic_baseline_image_24
        const val ROUND_CORNER_RADIUS = 16f
    }
}