package com.podcasttime.data

import com.podcasttime.data.model.Category
import com.podcasttime.data.model.Podcast

data class HomePodcastRow(
  val category: Category,
  val podcasts: List<Podcast>,
)
