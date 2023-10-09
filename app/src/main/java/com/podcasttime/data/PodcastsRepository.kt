package com.podcasttime.data

import com.podcasttime.data.model.Category
import com.podcasttime.data.model.Episode
import com.podcasttime.data.model.Podcast
import com.podcasttime.data.model.PodcastDetail
import com.podcasttime.data.remote.RemoteSource
import com.podcasttime.data.remote.mapper.toPodcastDetail
import javax.inject.Inject

interface PodcastsRepository {
  suspend fun getPodcastCatalog(): List<HomePodcastRow>
  suspend fun getPodcast(id: String): PodcastDetail
  suspend fun getEpisodeList(id: String): List<Episode>
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
            id = it.id.orEmpty(),
            title = it.title.orEmpty(),
            image = "https://bucket.sedagard.com/${it.image}",
          )
        } ?: emptyList(),
      )
    }
  }

  override suspend fun getPodcast(id: String): PodcastDetail {
    return remoteSource.getPodcast(id).toPodcastDetail()
  }

  override suspend fun getEpisodeList(id: String): List<Episode> {
    val episodeList = mutableListOf<Episode>()
    val documents = remoteSource.getPodcast(id)
      .aggregations
      .documents

    documents.forEach { document ->
      val episode = Episode(
        title = document.contents.title.orEmpty(),
        duration = document.contents.duration.orEmpty(),
        fileUrl = document.contents.fileUrl.orEmpty(),
        publicationDate = document.contents.publicationDate.orEmpty(),
      )
      episodeList.add(episode)
    }
    return episodeList
  }
}
