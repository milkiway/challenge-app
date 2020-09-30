package com.challenge.challengeapp.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.challenge.challengeapp.R
import com.challenge.challengeapp.database.dao.AlbumPhotos
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.view.CommonViewModel
import com.challenge.challengeapp.view.detail.adapter.AlbumAdapter
import com.challenge.challengeapp.view.detail.adapter.TopAdapter
import com.cruxlab.sectionedrecyclerview.lib.PositionManager
import com.cruxlab.sectionedrecyclerview.lib.SectionDataManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val commonViewModel by activityViewModels<CommonViewModel>()
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var sectionDataManager: SectionDataManager
    private lateinit var posManager: PositionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        commonViewModel.selectedPost?.let { viewModel.withPost(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       setupRecyclerView()

        viewModel.albumsLiveData?.observe(viewLifecycleOwner, { albumsWithPhotos ->
            viewModel.post?.let { post ->
                createSections(post, albumsWithPhotos)
//                val adapter = AlbumsAdapter(requireContext(), post.title, post.body, albumPhotos)
//                postAlbums.adapter = adapter
//                val decor = StickyHeaderDecoration(adapter)
//                postAlbums.addItemDecoration(decor, 0)
//                postAlbums.addItemDecoration(StickyHeaderDecoration(postAlbums, adapter))
            }
        })
    }

    private fun createSections(post: Post, albumsWithPhotos: List<AlbumPhotos>) {
        sectionDataManager.addSection(TopAdapter(R.layout.holder_post_title, post.title))
        sectionDataManager.addSection(TopAdapter(R.layout.holder_post_body, post.body))
        albumsWithPhotos.forEachIndexed { index, albumPhotos ->
            albumPhotos.album?.let { album ->
                sectionDataManager.addSection(AlbumAdapter(album, albumPhotos.photos), index.toShort())
            }
        }
    }

    private fun setupRecyclerView() {
        sectionDataManager = SectionDataManager()
        posManager = sectionDataManager
        val layoutManager = GridLayoutManager(context, SPAN_COUNT_PHOTOS)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (posManager.isHeader(position) || posManager.calcSection(position) == 0 || posManager.calcSection(position) == 1)
                    layoutManager.spanCount
                else
                    1
                }
        }
        postAlbums.layoutManager = layoutManager
        postAlbums.setHasFixedSize(false)
        val adapter = sectionDataManager.adapter
        postAlbums.adapter = adapter
        section_header_layout.attachTo(postAlbums, sectionDataManager);
    }

    companion object {
        const val SPAN_COUNT_PHOTOS: Int = 5
    }
}