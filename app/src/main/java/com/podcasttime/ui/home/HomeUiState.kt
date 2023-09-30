package com.podcasttime.ui.home

import com.podcasttime.data.HomePodcastRow

data class HomeUiState(
  val isLoading: Boolean = false,
  val podcasts: List<HomePodcastRow> = emptyList(),
  val error: String = "",
)
