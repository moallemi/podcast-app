package com.podcasttime.data.remote

import com.podcasttime.AppAssetManager
import com.podcasttime.data.remote.dto.EpisodeResponseDto
import com.podcasttime.data.remote.dto.ProductCatalogResponseDto
import com.podcasttime.data.remote.dto.SinglePodcastResponseDto
import com.podcasttime.dispatchers.DefaultDispatcherProvider
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

interface RemoteSource {

  suspend fun getPodcastCatalog(): List<ProductCatalogResponseDto>

  suspend fun getPodcast(id: String): SinglePodcastResponseDto

  suspend fun getEpisodeList(id: String): List<EpisodeResponseDto>
}

class FakeRemoteSource @Inject constructor(
  private val dispatcherProvider: DefaultDispatcherProvider,
  private val appAssetManager: AppAssetManager,
  private val json: Json,
) : RemoteSource {

  override suspend fun getPodcastCatalog(): List<ProductCatalogResponseDto> {
    return withContext(dispatcherProvider.io()) {
      appAssetManager.openFile("home.json")
        .use { inputStream -> json.decodeFromStream(inputStream) }
    }
  }

  override suspend fun getPodcast(id: String): SinglePodcastResponseDto {
    return withContext(dispatcherProvider.io()) {
      appAssetManager.openFile("podcast.json")
        .use { inputStream -> json.decodeFromStream(inputStream) }
    }
  }

  override suspend fun getEpisodeList(id: String): List<EpisodeResponseDto> {
    return withContext(dispatcherProvider.io()) {
      appAssetManager.openFile("podcast.json")
        .use { inputStream -> json.decodeFromStream(inputStream) }
    }
  }
}
