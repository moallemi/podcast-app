package com.podcasttime.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.podcasttime.data.HomePodcastRow
import com.podcasttime.data.model.Category
import com.podcasttime.data.model.Podcast
import com.podcasttime.ui.home.components.PodcastItem

@Composable
fun HomeScreen(
  state: HomeUiState,
  onPodcastClick: (podcastId: String) -> Unit,
) {
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
    },
    content = { paddingValues ->
      HomeContent(
        state = state,
        onPodcastClick = onPodcastClick,
        paddingValues = paddingValues,
      )
    },
  )
}

@Composable
fun HomeContent(
  state: HomeUiState,
  paddingValues: PaddingValues,
  onPodcastClick: (podcastId: String) -> Unit,
) {
  LazyColumn(contentPadding = paddingValues) {
    items(state.podcasts) { podcast ->
      PodcastItem(
        item = podcast,
        onPodcastClick = onPodcastClick,
      )
    }
  }
}

@Preview
@Composable
fun HomeScreenPreview() {
  val mockState = HomeUiState(isLoading = false, mockHomePodcastRow)
  HomeScreen(state = mockState, onPodcastClick = {})
}

val mockPodcasts = listOf(
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "https://via.placeholder.com/150/56a8c2",
  ),
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "https://via.placeholder.com/150/56a8c2",
  ),
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "https://via.placeholder.com/150/56a8c2",
  ),
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "https://via.placeholder.com/150/56a8c2",
  ),
)

val mockHomePodcastRow = listOf(
  HomePodcastRow(Category("Check"), mockPodcasts),
  HomePodcastRow(Category("Check2"), mockPodcasts),
)
