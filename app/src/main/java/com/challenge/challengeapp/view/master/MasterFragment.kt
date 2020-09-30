package com.challenge.challengeapp.view.master

import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isNotEmpty
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.challenge.challengeapp.R
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.databinding.FragmentListBinding
import com.challenge.challengeapp.view.CommonViewModel
import com.challenge.challengeapp.view.master.adapter.OnPostDeletedListener
import com.challenge.challengeapp.view.master.adapter.OnPostSelectionListener
import com.challenge.challengeapp.view.master.adapter.PostsAdapter
import com.challenge.challengeapp.view.master.adapter.PostsLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MasterFragment : Fragment(), OnPostDeletedListener {
    private val commonViewModel by activityViewModels<CommonViewModel>()
    private val viewModel: MasterViewModel by viewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var onPostSelectedListener: OnPostSelectionListener
    private lateinit var adapter: PostsAdapter
    private var searchJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        search()
        binding.retryButton.setOnClickListener { adapter.retry() }
    }

    private fun setupRecyclerView() {
        binding.postsRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter = PostsAdapter(onPostSelectedListener, this)
        binding.postsRecyclerView.adapter = adapter
        binding.postsRecyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = PostsLoadStateAdapter { adapter.retry() },
            footer = PostsLoadStateAdapter { adapter.retry() }
        )

        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.postsRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retry.isVisible = loadState.source.refresh is LoadState.Error || loadState.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.refresh as? LoadState.Error
                ?: loadState.source.refresh as? LoadState.Error
                ?: loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(requireContext(), "\uD83D\uDE28 Wooops ${it.error}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onPostSelectedListener = context as OnPostSelectionListener
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (menu.isNotEmpty())
            menu.clear()

        inflater.inflate(R.menu.list_fragment_menu, menu)
        val actionView = menu.findItem(R.id.action_search)?.actionView as SearchView

        actionView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean = search(query)
            override fun onQueryTextChange(newText: String): Boolean = search(newText)
        })
    }

    override fun onPostDeleted(post: Post) {
        viewModel.deletePost(post)
        commonViewModel.onDeletePost.postValue(post)
    }

    private fun search(query: String? = null): Boolean {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepo(query).collectLatest { adapter.submitData(it) }
        }
        return true
    }
}