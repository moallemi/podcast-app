package com.podcasttime.ui.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.podcasttime.data.model.Episode
import com.podcasttime.data.model.PodcastDetail

@Composable
fun PodcastDetailScreen(
  podcastId: String,
) {
  val viewModel = hiltViewModel<PodcastDetailViewModel>()
  val state by viewModel.state.collectAsStateWithLifecycle()

  LaunchedEffect(key1 = Unit) {
    viewModel.loadPodcast(podcastId)
  }

  PodcastDetailContent(
    state = state,
  )
}

@Composable
fun PodcastDetailContent(
  state: PodcastDetailUiState,
) {
  if (state.isLoading) {
    CircularProgressIndicator(
      modifier = Modifier.wrapContentSize(),
    )
  } else if (state.podcastDetail != null) {
    Column {
      PodcastDetail(
        podcastDetail = state.podcastDetail,
      )
      // TODO: handle empty state
      EpisodeList(episodeList = state.episodeList ?: emptyList())
    }
  } else {
    // TODO: Add error state
  }
}

@Composable
fun PodcastDetail(
  podcastDetail: PodcastDetail,
) {
  Row {
    AsyncImage(
      modifier = Modifier
        .size(128.dp),
      model = podcastDetail.image,
      contentDescription = null,
    )

    Column {
      Text(
        text = podcastDetail.title,
        style = MaterialTheme.typography.titleLarge,
      )
      Text(text = podcastDetail.author)
      Text(text = podcastDetail.keywords)
    }
  }
}

@Composable
fun EpisodeList(
  episodeList: List<Episode>,
) {
  LazyColumn {
    items(episodeList) { episode ->
      EpisodeItem(episode = episode)
    }
  }
}

@Composable
fun EpisodeItem(
  episode: Episode,
) {
  Text(text = episode.title)
}
