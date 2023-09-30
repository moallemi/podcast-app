package com.podcasttime.data.remote

import com.podcasttime.data.remote.dto.ProductCatalogResponseDto
import retrofit2.http.GET

interface RetrofitApi {

  @GET("podcast-catalog")
  suspend fun getPodcastCatalog(): List<ProductCatalogResponseDto>
}
