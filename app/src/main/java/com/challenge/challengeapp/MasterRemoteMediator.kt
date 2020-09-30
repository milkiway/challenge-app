package com.challenge.challengeapp

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.challenge.challengeapp.network.api.ApiServiceHelper
import com.challenge.challengeapp.database.AppDatabase
import com.challenge.challengeapp.database.entities.PostUserModel
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MasterRemoteMediator (private val database: AppDatabase, private val apiServiceHelper: ApiServiceHelper)
  : RemoteMediator<Int, PostUserModel>() {

  /* This is an example of pagination mediator
    Sample api does not provide pagination, so all data is loaded at once
   */
  override suspend fun load(
    loadType: LoadType,
    state: PagingState<Int, PostUserModel>
  ): MediatorResult {
    try {
      Log.d(TAG, "loading... $loadType")
      if (loadType == LoadType.REFRESH) {
        // Suspending network load via Retrofit. This doesn't need to be
        // wrapped in a withContext(Dispatcher.IO) { ... } block since
        // Retrofit's Coroutine CallAdapter dispatches on a worker
        // thread.
        val albums = apiServiceHelper.getAlbums()
        val photos = apiServiceHelper.getPhotos()
        val posts = apiServiceHelper.getPosts()
        val users = apiServiceHelper.getUsers()

        database.withTransaction {
            // remove all data before insertion of new records
            database.clearAllTables()

          // Insert new data into database, which invalidates the
          // current PagingData, allowing Paging to present the updates
          // in the DB.
          database.users().insertAll(users)
          database.posts().insertAll(posts)
          database.albums().insertAll(albums)
          database.photos().insertAll(photos)
        }
      }
      return MediatorResult.Success(endOfPaginationReached = true)
    } catch (e: IOException) {
      Log.d(TAG, "$e.message")
      return MediatorResult.Error(e)
    } catch (e: HttpException) {
      Log.d(TAG, "$e.message")
      return MediatorResult.Error(e)
    }
  }

  companion object {
    const val TAG = "MasterRemoteMediator"
  }
}