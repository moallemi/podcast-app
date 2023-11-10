package com.podcasttime.ui.home

import com.podcasttime.data.HomePodcastRow
import com.podcasttime.data.model.Category

data class HomeUiState(
  val isLoading: Boolean = false,
  val podcasts: List<HomePodcastRow> = emptyList(),
  val categories: List<Category> = emptyList(),
  val error: String = "",
)
