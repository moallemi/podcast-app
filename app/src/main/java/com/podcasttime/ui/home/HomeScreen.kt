package com.podcasttime.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
  ScrollableTabRow(
    selectedTabIndex = selectedTabIndex,
    edgePadding = 4.dp,
    divider = {}, // no need for divider -> plain tab
    indicator = {}, // no need for indicator -> plain tab
    tabs = {
      categories.forEachIndexed { index, category ->
        TabChips(
          selected = selectedTabIndex == index,
          index = index,
          category = category,
        )
      }
    },
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabChips(selected: Boolean, index: Int, category: Category) {
  Tab(
    selected = selected,
    onClick = { /*TODO*/ },
    modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
  ) {
    FilterChip(
      selected = selected,
      onClick = { /*TODO*/ },
      label = {
        Text(text = category.title)
      },
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
