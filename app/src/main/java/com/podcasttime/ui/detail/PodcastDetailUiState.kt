package com.podcasttime.ui.detail

import com.podcasttime.data.model.Episode
import com.podcasttime.data.model.PodcastDetail

data class PodcastDetailUiState(
  val isLoading: Boolean = false,
  val podcastDetail: PodcastDetail? = null,
  val isLoadingEpisode: Boolean = false,
  val episodeList: List<Episode>? = null,
)
