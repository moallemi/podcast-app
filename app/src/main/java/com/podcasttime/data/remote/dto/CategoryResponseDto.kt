package com.podcasttime.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryResponseDto(
  val subCategories: List<CategoryResponseDto>? = null,
  val id: Long? = null,
  val name: String? = null,
  val title: String? = null,
  val priority: Long? = null,

  @SerialName("parentId")
  val parentID: Long? = null,
)
