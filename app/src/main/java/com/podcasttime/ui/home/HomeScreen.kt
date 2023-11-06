package com.podcasttime.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.podcasttime.data.HomePodcastRow
import com.podcasttime.data.model.Category
import com.podcasttime.data.model.Podcast
import com.podcasttime.ui.home.components.PodcastItem

@Composable
fun HomeScreen(
  state: HomeUiState,
  onPodcastClick: (podcastId: String) -> Unit,
) {
  val categories = state.podcasts.map { it.category }
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      HomeTopAppBar(categories)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(categories: List<Category>) {
  Column(modifier = Modifier.fillMaxWidth()) {
    CenterAlignedTopAppBar(
      navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
          Icon(
            modifier = Modifier.rotate(180f),
            imageVector = Icons.Default.ExitToApp,
            contentDescription = "Exit the app",
          )
        }
      },
      title = {
        Text(text = "Podcast Time")
      },
    )
    CategoryTabs(categories)
  }
}

@Composable
fun CategoryTabs(categories: List<Category>) {
  val selectedTabIndex = 1
  LazyRow(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(horizontal = 8.dp),
  ) {
    itemsIndexed(categories) { index: Int, category: Category ->
      CategoryChip(selected = selectedTabIndex == index, index = index, text = category.title)
    }
  }
}

@Composable
fun CategoryChip(selected: Boolean, index: Int, text: String) {
  Surface(
    color = if (selected) {
      MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
    } else {
      MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
    },
    shape = MaterialTheme.shapes.large,
    contentColor = if (selected) {
      MaterialTheme.colorScheme.primary
    } else {
      MaterialTheme.colorScheme.onSurface
    },
  ) {
    Text(
      modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
      text = text,
      style = MaterialTheme.typography.bodyMedium,
    )
  }
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
