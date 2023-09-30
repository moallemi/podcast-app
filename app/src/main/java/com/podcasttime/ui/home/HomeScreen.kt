package com.podcasttime.ui.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.podcasttime.ui.home.components.PodcastItem

@Composable
fun HomeScreen() {
  val viewModel = hiltViewModel<HomeViewModel>()
  val state by viewModel.state.collectAsStateWithLifecycle()

  HomeContent(
    state = state,
  )
}

@Composable
private fun HomeContent(
  state: HomeUiState,
) {
  LazyColumn() {
    items(state.podcasts) { podcast ->
      PodcastItem(
        item = podcast,
      )
    }
  }
}
