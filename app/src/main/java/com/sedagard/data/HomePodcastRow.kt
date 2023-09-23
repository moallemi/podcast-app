package com.sedagard.data

import com.sedagard.data.model.Category
import com.sedagard.data.model.Podcast

data class HomePodcastRow(
  val category: Category,
  val podcasts: List<Podcast>,
)
