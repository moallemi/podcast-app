package com.podcasttime.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.podcasttime.ui.home.components.PodcastItem

@Composable
fun HomeScreen(
  state: HomeUiState,
  onPodcastClick: (podcastId: String) -> Unit,
) {
  LazyColumn() {
    items(state.podcasts) { podcast ->
      PodcastItem(
        item = podcast,
        onPodcastClick = onPodcastClick,
      )
    }
  }
}
