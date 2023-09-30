package com.podcasttime.ui.home.components

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.podcasttime.data.HomePodcastRow
import com.podcasttime.data.model.Podcast

@Composable
fun PodcastItem(item: HomePodcastRow) {
  Column {
    Text(
      modifier = Modifier
        .padding(top = 8.dp)
        .padding(horizontal = 16.dp),
      text = item.category.title,
      style = MaterialTheme.typography.titleMedium,
      fontWeight = FontWeight.Bold,
    )
    PodcastList(item.podcasts)
  }
}

@Composable
private fun PodcastList(podcasts: List<Podcast>) {
  LazyRow(
    contentPadding = PaddingValues(16.dp),
    horizontalArrangement = spacedBy(8.dp),
  ) {
    items(podcasts) { podcast ->
      Card(
        modifier = Modifier
          .width(128.dp),
      ) {
        Column {
          AsyncImage(
            modifier = Modifier
              .size(128.dp),
            model = podcast.image,
            contentDescription = null,
          )
          Text(
            modifier = Modifier
              .padding(8.dp),
            text = podcast.title,
            style = MaterialTheme.typography.bodyMedium,
            minLines = 2,
            maxLines = 2,
          )
        }
      }
    }
  }
}
