package com.challenge.challengeapp.view

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.challenge.challengeapp.R
import com.challenge.challengeapp.database.entities.Post
import com.challenge.challengeapp.util.navigateAndPopUpTo
import com.challenge.challengeapp.view.master.adapter.OnPostSelectionListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnPostSelectionListener {

    private val commonViewModel: CommonViewModel by viewModels()
    private var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        isTwoPane = resources.getBoolean(R.bool.isLandscape)
        if (!isTwoPane)
            getNavController()?.let { setupActionBarWithNavController(it) }
        else
            setTitle(R.string.challenge_accepted)

        subscribeOnDeletePost()
    }

    private fun subscribeOnDeletePost() {
        commonViewModel.onDeletePost.observe(this, { showEmptyFragment() })
    }

    override fun onPostSelected(post: Post) {
        commonViewModel.selectedPost = post
        showDetailFragment()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                if (commonViewModel.selectedPost != null && !isTwoPane) {
                    commonViewModel.selectedPost = null
                }
                getNavController()?.navigateUp()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showDetailFragment() {
        val navController = getNavController()
        val detailFragmentId = R.id.detailFragment
        if (isTwoPane)
            navController?.navigateAndPopUpTo(detailFragmentId)
        else
            navController?.navigate(detailFragmentId)
    }

    private fun showEmptyFragment() {
        if (isTwoPane) {
            getNavController()?.navigate(R.id.emptyFragment)
        }
    }

    private fun getNavController() = getNavHost()?.navController

    private fun getNavHost(): NavHostFragment? = supportFragmentManager.findFragmentById(if (isTwoPane) R.id.detail_container else R.id.main_container) as? NavHostFragment
}