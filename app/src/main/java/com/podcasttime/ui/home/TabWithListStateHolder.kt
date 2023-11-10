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

const val LIST_STATE_COLLECTING_DELAY = 100L

class TabWithListStateHolder(
  private val coroutineScope: CoroutineScope,
  private val itemListState: LazyListState,
  private val tabListState: LazyListState,
  private val tabIndices: List<Int>,
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
      }.debounce(LIST_STATE_COLLECTING_DELAY)
        .collectLatest {
          if (tabIndices.isNotEmpty()) {
            var itemPosition = itemListState.findFirstFullyVisibleItemIndex()

            if (itemPosition == -1) {
              itemPosition = itemListState.firstVisibleItemIndex
            }

            if (itemPosition == -1) {
              return@collectLatest
            }

            if ((tabIndices.last() == itemListState.findLastFullyVisibleItemIndex()) &&
              (selectedTab !in itemListState.firstVisibleItemIndex until tabIndices.last())
            ) {
              itemPosition = tabIndices.last()
            }

            if (itemPosition != selectedTab) {
              selectedTab = itemPosition
              tabListState.animateScrollToItem(itemPosition)
            }
          }
        }
    }
  }
}

fun LazyListState.findFirstFullyVisibleItemIndex(): Int =
  findFullyVisibleItemIndex(reversed = false)

fun LazyListState.findLastFullyVisibleItemIndex(): Int = findFullyVisibleItemIndex(reversed = true)
fun LazyListState.findFullyVisibleItemIndex(reversed: Boolean): Int {
  layoutInfo.visibleItemsInfo.run { if (reversed) reversed() else this }.forEach { itemInfo ->
    val itemStartOffset = itemInfo.offset
    val itemEndOffset = itemInfo.offset + itemInfo.size
    val viewportStartOffset = layoutInfo.viewportStartOffset
    val viewportEndOffset = layoutInfo.viewportEndOffset
    if (itemStartOffset >= viewportStartOffset && itemEndOffset <= viewportEndOffset) {
      return itemInfo.index
    }
  }
  return -1
}
