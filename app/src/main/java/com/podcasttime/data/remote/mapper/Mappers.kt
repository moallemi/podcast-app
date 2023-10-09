package com.podcasttime.data.remote.mapper

import com.podcasttime.data.model.PodcastDetail
import com.podcasttime.data.remote.dto.SinglePodcastResponseDto

fun SinglePodcastResponseDto.toPodcastDetail(): PodcastDetail {
  return PodcastDetail(
    id = podcast.id.orEmpty(),
    title = podcast.title.orEmpty(),
    description = podcast.description.orEmpty(),
    image = "https://bucket.sedagard.com/${podcast.image}",
    author = podcast.author.orEmpty(),
    keywords = podcast.keywords.orEmpty(),
  )
}
