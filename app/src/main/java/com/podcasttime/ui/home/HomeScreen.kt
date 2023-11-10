package com.podcasttime.ui.home

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalContext
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
  val podcastListState = rememberLazyListState()

  Scaffold(
    modifier = Modifier.fillMaxSize(),
    topBar = {
      HomeTopAppBar(
        categories = state.categories,
        podcastListState = podcastListState,
      )
    },
    content = { paddingValues ->
      HomeContent(
        homeUiState = state,
        onPodcastClick = onPodcastClick,
        paddingValues = paddingValues,
        listState = podcastListState,
      )
    },
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
  categories: List<Category>,
  podcastListState: LazyListState,
) {
  val tabListState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()

  val tabWithListStateHolder = remember(categories) {
    TabWithListStateHolder(
      coroutineScope = coroutineScope,
      itemListState = podcastListState,
      tabListState = tabListState,
      tabIndices = categories.indices.toList(),
    )
  }
  val activity = LocalContext.current as? Activity

  Column(modifier = Modifier.fillMaxWidth()) {
    CenterAlignedTopAppBar(
      navigationIcon = {
        ExitButton(
          onExitClicked = {
            activity?.finish()
          },
        )
      },
      title = {
        Text(text = "Podcast Time")
      },
      colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
        titleContentColor = MaterialTheme.colorScheme.onPrimary,
      ),
    )
    CategoryTabs(
      categories = categories,
      selectedTabIndex = tabWithListStateHolder.selectedTab,
      onCategoryClicked = tabWithListStateHolder.onTabClicked,
      tabListState = tabListState,
    )
  }
}

@Composable
fun ExitButton(onExitClicked: () -> Unit) {
  IconButton(onClick = { onExitClicked() }) {
    Icon(
      modifier = Modifier.rotate(180f),
      imageVector = Icons.Default.ExitToApp,
      contentDescription = "Exit the app",
    )
  }
}

@Composable
fun CategoryTabs(
  categories: List<Category>,
  selectedTabIndex: Int,
  onCategoryClicked: (Int) -> Unit,
  tabListState: LazyListState,
) {
  LazyRow(
    modifier = Modifier
      .fillMaxWidth()
      .background(MaterialTheme.colorScheme.primary),
    horizontalArrangement = Arrangement.spacedBy(8.dp),
    contentPadding = PaddingValues(horizontal = 8.dp),
    state = tabListState,
  ) {
    itemsIndexed(categories) { index: Int, category: Category ->
      CategoryChip(
        selected = selectedTabIndex == index,
        index = index,
        text = category.title,
        onCategoryClicked = onCategoryClicked,
      )
    }
  }
}

@Composable
fun CategoryChip(selected: Boolean, index: Int, text: String, onCategoryClicked: (Int) -> Unit) {
  Surface(
    color = if (selected) {
      MaterialTheme.colorScheme.secondary
    } else {
      MaterialTheme.colorScheme.surface
    },
    shape = MaterialTheme.shapes.large,
    contentColor = if (selected) {
      MaterialTheme.colorScheme.onSecondary
    } else {
      MaterialTheme.colorScheme.onSurface
    },
    onClick = {
      onCategoryClicked(index)
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
  listState: LazyListState,
  homeUiState: HomeUiState,
  paddingValues: PaddingValues,
  onPodcastClick: (podcastId: String) -> Unit,
) {
  LazyColumn(contentPadding = paddingValues, state = listState) {
    items(homeUiState.podcasts) { podcast ->
      PodcastItem(
        item = podcast,
        onPodcastClick = onPodcastClick,
      )
      Divider()
    }
  }
}

@Preview
@Composable
fun HomeScreenPreview() {
  val mockState =
    HomeUiState(isLoading = false, podcasts = mockHomePodcastRow, categories = mockCategories)
  HomeScreen(state = mockState, onPodcastClick = {})
}

val mockPodcasts = listOf(
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "",
  ),
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "",
  ),
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "",
  ),
  Podcast(
    id = "1",
    title = "Sample 1",
    image = "",
  ),
)
val mockCategories = listOf(Category("Check"), Category("Check2"))

val mockHomePodcastRow = listOf(
  HomePodcastRow(mockCategories[0], mockPodcasts),
  HomePodcastRow(mockCategories[1], mockPodcasts),
)
