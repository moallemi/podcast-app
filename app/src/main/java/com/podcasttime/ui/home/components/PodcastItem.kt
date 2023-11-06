package com.podcasttime.ui.home.components

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.podcasttime.R
import com.podcasttime.data.HomePodcastRow
import com.podcasttime.data.model.Podcast

@Composable
fun PodcastItem(
  item: HomePodcastRow,
  onPodcastClick: (podcastId: String) -> Unit,
) {
  Column {
    Text(
      modifier = Modifier
        .padding(top = 8.dp)
        .padding(horizontal = 16.dp),
      text = item.category.title,
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Bold,
    )
    PodcastList(
      podcasts = item.podcasts,
      onPodcastClick = onPodcastClick,
    )
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PodcastList(
  podcasts: List<Podcast>,
  onPodcastClick: (podcastId: String) -> Unit,
) {
  val rowsCount = 2
  val lazyListHeight = getLazyListHeight(
    rowsCount,
    dimensionResource(id = R.dimen.podcast_card_height),
    dimensionResource(id = R.dimen.grid_list_vertical_padding),
  )
  LazyHorizontalGrid(
    modifier = Modifier.height(lazyListHeight),
    rows = GridCells.Fixed(rowsCount),
    contentPadding = PaddingValues(16.dp),
    horizontalArrangement = spacedBy(dimensionResource(id = R.dimen.grid_list_horizontal_padding)),
    verticalArrangement = spacedBy(dimensionResource(id = R.dimen.grid_list_vertical_padding)),
  ) {
    items(podcasts) { podcast ->
      Card(
        modifier = Modifier
          .width(128.dp)
          .height(192.dp),
        onClick = { onPodcastClick(podcast.id) },
      ) {
        Column {
          AsyncImage(
            modifier = Modifier
              .width(128.dp)
              .weight(4f),
            model = podcast.image,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
          )
          Text(
            modifier = Modifier
              .padding(8.dp)
              .fillMaxWidth()
              .weight(1f),
            text = podcast.title,
            style = MaterialTheme.typography.bodyMedium,
            minLines = 2,
            maxLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
          )
        }
      }
    }
  }
}

fun getLazyListHeight(rowsCount: Int, itemHeight: Dp, itemVerticalPadding: Dp): Dp {
  return (itemHeight * rowsCount) +
    (itemVerticalPadding * (rowsCount - 1))
}
