package com.podcasttime.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeRoute(
  onPodcastClick: (podcastId: String) -> Unit,
) {
  val viewModel = hiltViewModel<HomeViewModel>()
  val state by viewModel.state.collectAsStateWithLifecycle()

  HomeScreen(
    state = state,
    onPodcastClick = onPodcastClick,
  )
}
