package com.sedagard.data.remote

import com.sedagard.AppAssetManager
import com.sedagard.data.remote.dto.ProductCatalogResponseDto
import com.sedagard.dispatchers.DefaultDispatcherProvider
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import javax.inject.Inject

interface RemoteSource {

  suspend fun getPodcastCatalog(): List<ProductCatalogResponseDto>
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
}
