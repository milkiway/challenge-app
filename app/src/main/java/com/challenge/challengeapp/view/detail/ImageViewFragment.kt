package com.challenge.challengeapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.challenge.challengeapp.R
import kotlinx.android.synthetic.main.fragment_image_view.*

class ImageViewFragment : Fragment() {
    private val args: ImageViewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
            = inflater.inflate(R.layout.fragment_image_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView.load(args.imageUrl)
        imageView.setOnClickListener { findNavController(imageView).navigateUp() }
    }
}