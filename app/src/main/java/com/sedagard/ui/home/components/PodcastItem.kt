package com.sedagard.ui.home.components

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sedagard.data.HomePodcastRow
import com.sedagard.data.model.Podcast

@Composable
fun PodcastItem(item: HomePodcastRow) {
  Column {
    Text(text = item.category.title)
    PodcastList(item.podcasts)
  }
}

@Composable
private fun PodcastList(podcasts: List<Podcast>) {
  LazyRow(
    horizontalArrangement = spacedBy(16.dp),
  ) {
    items(podcasts) { podcast ->
      Card(
        modifier = Modifier
          .width(128.dp)
          .height(164.dp),
      ) {
        Column {
          AsyncImage(
            modifier = Modifier
              .size(128.dp),
            model = podcast.image,
            contentDescription = null,
          )
          Text(text = podcast.title.orEmpty())
        }
      }
    }
  }
}
