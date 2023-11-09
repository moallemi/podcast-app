package com.podcasttime.ui.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class TabWithListStateHolder(
  private val coroutineScope: CoroutineScope,
  private val itemListState: LazyListState,
  private val tabListState: LazyListState,
  private val categoryIndices: List<Int>,
) {
  var selectedTab by mutableIntStateOf(0)

  init {
    observeListChanges()
  }

  val onTabClicked: (index: Int) -> Unit = { index ->
    coroutineScope.launch {
      selectedTab = index
      tabListState.animateScrollToItem(index)
      itemListState.scrollToItem(index)
    }
  }

  @OptIn(FlowPreview::class)
  private fun observeListChanges() {
    coroutineScope.launch {
      snapshotFlow {
        itemListState.layoutInfo
      }.debounce(100) // add a delay to give tabListState.animateScrollToItem time to scroll to desired pos
        .collectLatest {
          var itemPosition = itemListState.firstVisibleItemIndex
          if (itemListState.layoutInfo.visibleItemsInfo.isNotEmpty()) {
            if (categoryIndices.isNotEmpty()) {
              if (categoryIndices.last() == itemListState.layoutInfo.visibleItemsInfo.last().index) {
                itemPosition = itemListState.layoutInfo.visibleItemsInfo.last().index
              }
            }
          }
          if (itemPosition != selectedTab) {
            selectedTab = itemPosition
            tabListState.animateScrollToItem(itemPosition)
          }
        }
    }
  }
}
