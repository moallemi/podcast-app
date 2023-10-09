package com.podcasttime.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class EpisodeResponseDto(
  val title: String?,
  val duration: String?,
  val fileUrl: String?,
  val publicationDate: String?,
)
