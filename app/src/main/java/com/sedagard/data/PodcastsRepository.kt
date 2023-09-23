package com.sedagard.data

import com.sedagard.data.model.Category
import com.sedagard.data.model.Podcast
import com.sedagard.data.remote.RemoteSource
import javax.inject.Inject

interface PodcastsRepository {
  suspend fun getPodcastCatalog(): List<HomePodcastRow>
}

class PodcastsRepositoryImpl @Inject constructor(
  private val remoteSource: RemoteSource,
) : PodcastsRepository {

  override suspend fun getPodcastCatalog(): List<HomePodcastRow> {
    return remoteSource.getPodcastCatalog().map {
      HomePodcastRow(
        category = Category(
          title = it.category?.title.orEmpty(),
        ),
        podcasts = it.podcasts?.map {
          Podcast(
            title = it.title.orEmpty(),
            image = "https://bucket.sedagard.com/${it.image}",
          )
        } ?: emptyList(),
      )
    }
  }
}
