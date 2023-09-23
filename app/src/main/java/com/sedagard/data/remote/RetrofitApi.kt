package com.sedagard.data.remote

import com.sedagard.data.remote.dto.ProductCatalogResponseDto
import retrofit2.http.GET

interface RetrofitApi {

  @GET("podcast-catalog")
  suspend fun getPodcastCatalog(): List<ProductCatalogResponseDto>
}
