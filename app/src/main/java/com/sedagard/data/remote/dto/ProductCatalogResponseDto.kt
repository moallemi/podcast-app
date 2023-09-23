package com.sedagard.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class ProductCatalogResponseDto(
  val category: CategoryResponseDto? = null,
  val isRootItem: Boolean? = null,
  val podcasts: List<PodcastResponseDto>? = null,
)
