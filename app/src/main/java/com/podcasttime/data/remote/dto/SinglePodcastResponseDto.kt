package com.podcasttime.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class SinglePodcastResponseDto(
  val podcast: PodcastResponseDto,
  val aggregations: AggregationsResponseDto,
)

@Serializable
data class AggregationsResponseDto(
  val documents: List<EpisodeContentsResponseDto>,
)

@Serializable
data class EpisodeContentsResponseDto(
  val contents: EpisodeResponseDto,
)
